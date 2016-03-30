package com.clinic.clinic.api.ws;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

import com.clinic.clinic.api.bizlogic.service.IAuthService;
import com.clinic.clinic.common.consts.IRestApiConstants;
import com.clinic.clinic.common.dto.biz.AccountDto;
import com.clinic.clinic.common.dto.biz.ClinicRightDto;
import com.clinic.clinic.common.exception.BizlogicException;
import com.clinic.clinic.common.exception.ClinicBizLogicException;

/**
 * <p>
 * The abstract controller contains common methods of children controllers
 * (including RESTful web-services).
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public abstract class AbsRestApi {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbsRestApi.class);
    
    @Autowired
    private IAuthService authService;

    /**
     * <p>
     * Checks authentication validation of caller. It will throw
     * {@link MaperBizLogicException} error when the caller is in invalid state
     * (no authorization, no logged session, timed out session ...).
     * </p>
     *
     * @throws MaperBizLogicException
     */
    protected void validateAuthStatus() throws ClinicBizLogicException {
        // TODO: throw MaperBizLogicException when caller is invalid
    }

    /**
     * <p>
     * Builds response header of paging navigation. Usually, it has following
     * format:<br/>
     * <code>Content-Range: 48-55/971</code><br/>
     * <code>Accept-Range: "resource" 10</code><br />
     * <code>Navigation-Link: "navigation URI links"</code>
     * </p>
     *
     * @param offset
     * @param limit
     * @param totalNumber
     * @param resourceName
     * @param maxPerPage
     * @param naviUriLinks
     * @param response
     */
    protected void buildPagingResponseHeader(final int offset, final int limit, final long totalNumber,
            final String resourceName, final int maxPerPage, final List<String> naviUriLinks,
            final HttpServletResponse response) {
        // TODO: validate offset, limit, totalNumber ...

        // TODO: set HTTP code based on given number: 200 OK, 206 Partial
        // Content, 400 Bad Request
        response.setHeader(IRestApiConstants.PAGING_RESPONSE_HEADER_CONTENT_RANGE_KEY, String
                .format(IRestApiConstants.PAGING_RESPONSE_HEADER_CONTENT_RANGE_FORMAT, offset, limit, totalNumber));
        response.setHeader(IRestApiConstants.PAGING_RESPONSE_HEADER_ACCEPT_RANGE_KEY,
                String.format(IRestApiConstants.PAGING_RESPONSE_HEADER_ACCEPT_RANGE_FORMAT, resourceName, maxPerPage));
        // TODO: add URI links of naviUriLinks (first, previous, next, last)
    }

    /**
     * <p>
     * Build "pageable" paging parameter which will be used by Spring-JPA data
     * layer.
     * </p>
     *
     * @param viewPageNo
     *            viewing page number
     * @param maxRecPerPage
     *            maximum records in a page
     * @param sortExpression
     *            {@link Sort} sorting expression, <code>null</code> when there
     *            is no sorting
     * @return {@link Pageable} with paging information, <code>null</code> when
     *         given value is invalid
     */
    protected Pageable buildPageableParam(final int viewPageNo, final int maxRecPerPage, final Sort sortExpression) {
        Pageable retRange = null;
        if (null != sortExpression) {
            retRange = new PageRequest(viewPageNo, maxRecPerPage, sortExpression);
        } else {
            retRange = new PageRequest(viewPageNo, maxRecPerPage);
        }
        return retRange;
    }

    /**
     * <p>
     * Parses "sort" parameter.
     * </p>
     *
     * @param sortExp
     *            {@link String} has format "sort=-salary|name"
     * @return {@link Sort} combination sorting expression, <code>null</code>
     *         when given value is invalid
     */
    protected Sort parseSortParam(String sortExp) {
        Sort retSorting = null;

        if (!StringUtils.isEmpty(sortExp)) {
            final String[] sptSortAttrs = sortExp.split(IRestApiConstants.SORTING_SEPARATOR);
            if (null != sptSortAttrs && sptSortAttrs.length > 0) {
                for (final String sortAttr : sptSortAttrs) {
                    Direction attrDirection = Direction.ASC;
                    int attrStartIdx = 0;
                    if (sortAttr.startsWith(IRestApiConstants.SORTING_DESC_MARK)) {
                        // Sorting by DESC
                        attrDirection = Direction.DESC;
                        attrStartIdx = IRestApiConstants.SORTING_DESC_MARK.length();
                    }
                    final String attrName = sortAttr.substring(attrStartIdx);
                    final Sort newAttrSort = new Sort(attrDirection, attrName);

                    if (null == retSorting) {
                        retSorting = newAttrSort;
                    } else {
                        retSorting = retSorting.and(newAttrSort);
                    }
                }
            }
        }

        return retSorting;
    }

    /**
     * <p>
     * Creates business logic exception and throws it to caller.
     * </p>
     *
     * @param msgKey
     * @param params
     */
    protected void throwMaperBizLogicException(final String msgKey, final String... params) {
        final ClinicBizLogicException be = new ClinicBizLogicException(msgKey);
        if (null != params && params.length > 0) {
            for (String param : params) {
                be.addParamValue(param);
            }
        }
        throw be;
    }
    
    protected AccountDto checkAuthenticated(final Integer sessionId, final String loginName) throws BizlogicException {
        return authService.checkAuthenticated(sessionId, loginName);
    }
    
    protected ClinicRightDto checkAuthorized(final String sessionId, final String rightCode) throws BizlogicException {
        return authService.hasRight(sessionId, rightCode);
    }
}
