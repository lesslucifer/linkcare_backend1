package com.clinic.clinic.api.ws.biz;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic.api.bizlogic.service.IMajorService;
import com.clinic.clinic.api.ws.AbsRestApi;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.consts.IRestApiConstants;
import com.clinic.clinic.common.consts.IRestApiUrlMaps;
import com.clinic.clinic.common.dto.biz.MajorDto;

@RestController
@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_GROUP)
public class MajorRestApi extends AbsRestApi {
	/** Logging property. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MajorRestApi.class);
	@Autowired
	private IMajorService majorService;

	@RequestMapping(value = IRestApiUrlMaps.REST_API_BIZ_MAJORS, method = RequestMethod.GET, produces = {
			"application/json" })
	public Page<MajorDto> getAllMajor(
			@RequestParam(value = IRestApiConstants.SORTING_PARAM_NAME_SORT, required = IDbConstants.FALSE) String sortExp,
			@RequestParam(value = IRestApiConstants.PAGING_PARAM_NAME_PAGE, required = IDbConstants.FALSE, defaultValue = "0") Integer viewPageNo,
			@RequestParam(value = IRestApiConstants.PAGING_PARAM_NAME_LIMIT, required = IDbConstants.FALSE, defaultValue = "10") Integer maxRecPerPage,
			HttpServletResponse response) {
	    if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
		Page<MajorDto> retVal = null;
		try {
			/**
			 * Call business service to acquire data.
			 */
			// Extract "sort expression" from query string
			final Sort sortingExp = parseSortParam(sortExp);

			// Build "pageable" parameter from query string
			final Pageable range = buildPageableParam(viewPageNo, maxRecPerPage, sortingExp);

			retVal = majorService.getAllMajor(range);

			/**
			 * Paging navigation processing.
			 */
			final int offset = viewPageNo * maxRecPerPage;
			final int limit = offset + maxRecPerPage;
			final long totalNumber = retVal.getContent().size();
			final String resourceName = MajorDto.class.getCanonicalName().toLowerCase();
			final List<String> naviUriLinks = new ArrayList<String>();
			// TODO: create navigation URI links
			buildPagingResponseHeader(offset, limit, totalNumber, resourceName, maxRecPerPage, naviUriLinks, response);
		} catch (Exception e) {
			LOGGER.error("Error", e);
		} finally {
		    if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
		}
		return retVal;
	}
}
