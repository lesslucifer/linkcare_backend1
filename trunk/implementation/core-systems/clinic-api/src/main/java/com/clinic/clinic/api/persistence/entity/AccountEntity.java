package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
@NamedQuery(name = "AccountEntity.findAll", query = "Select a From AccountEntity a")
public class AccountEntity extends TraceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3135124384140213026L;
	@Column(name = "login_name", nullable = false, length = 45)
	private String loginName;
	@Column(name="code", unique = true, nullable = false, length=45)
    private String code;
	@Column(name = "phone_number", nullable = false, length = 45)
	private String phoneNumber;
	@Column(name = "experience", nullable = false)
	private Double experience;
	@Column(name = "id_card", nullable = false, length = 45)
	private String idCard;
	@Column(name = "passport", nullable = true, length = 45)
	private String passport;
	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	@Column(name = "midle_name", nullable = true, length = 45)
	private String midleName;
	@Column(name = "gender", nullable = true, length = 45)
	private Integer gender;
	@Column(name = "birdthday", nullable = false, length = 45)
	private Long birdthday;
	@Column(name = "image_url", nullable = true, length = 500)
	private String avatar;
	@Column(name = "email", nullable = false, length = 45)
	private String email;
	@Column(name = "facebook", nullable = true, length = 45)
	private String facebook;
	@Column(name = "facebook_id", nullable = true, length = 45)
	private String facebookId;
	@Column(name = "google_plus_id", nullable = true, length = 45)
	private String googlePlusId;
	@Column(name = "website", nullable = true, length = 45)
	private String website;
	@Column(name = "hashed_password", nullable = false, length = 45)
	private String hashedPassword;
	@Column(name = "need_change_pwd", nullable = true)
	private Boolean needChangePWD;
	@Column(name = "active_flag", nullable = false)
	private Integer activeFlag = 0;
	@Column(name = "begin_active_time", nullable = false, length = 20)
	private Long beginActiveTime;
	@Column(name = "end_active_time", nullable = false, length = 20)
	private Long endActiveTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "annual_fee_id", nullable = false)
	private AnnualFeeEntity annualFee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id", nullable = true)
	private SubcategoryEntity subcategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id", nullable = true)
	private PlaceEntity place;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cost_id", nullable = true)
	private CostEntity cost;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	private List<CommentEntity> patients = new ArrayList<CommentEntity>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicar")
	private List<CommentEntity> medicars = new ArrayList<CommentEntity>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rate_id", nullable = true)
	private RateEntity rate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private List<CalendarEntity> calendars = new ArrayList<CalendarEntity>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	private List<AppointmentEntity> appointPatients = new ArrayList<AppointmentEntity>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicar")
	private List<AppointmentEntity> appointMedicars = new ArrayList<AppointmentEntity>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private List<SessionLogEntity> sessions = new ArrayList<SessionLogEntity>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partient")
	private List<RateTraceEntity> rateTraces = new ArrayList<RateTraceEntity>();
	
	//fk:account table and role table
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity=RoleEntity.class)
    @JoinTable(name = "account_role", catalog = "cliniccore_db",
    joinColumns = { @JoinColumn(name = "account_id", nullable = false, updatable = false) }, 
    inverseJoinColumns = { @JoinColumn(name = "role_id",nullable = false, updatable = false) })
    @JsonIgnore
    private List<RoleEntity> roles = new ArrayList<RoleEntity>();  
	
	public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMidleName() {
		return midleName;
	}

	public void setMidleName(String midleName) {
		this.midleName = midleName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Long getBirdthday() {
		return birdthday;
	}

	public void setBirdthday(Long birdthday) {
		this.birdthday = birdthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGooglePlusId() {
		return googlePlusId;
	}

	public void setGooglePlusId(String googlePlusId) {
		this.googlePlusId = googlePlusId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Boolean getNeedChangePWD() {
		return needChangePWD;
	}

	public void setNeedChangePWD(Boolean needChangePWD) {
		this.needChangePWD = needChangePWD;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Long getBeginActiveTime() {
		return beginActiveTime;
	}

	public void setBeginActiveTime(Long beginActiveTime) {
		this.beginActiveTime = beginActiveTime;
	}

	public Long getEndActiveTime() {
		return endActiveTime;
	}

	public void setEndActiveTime(Long endActiveTime) {
		this.endActiveTime = endActiveTime;
	}

	public AnnualFeeEntity getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(AnnualFeeEntity annualFee) {
		this.annualFee = annualFee;
	}

    public SubcategoryEntity getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryEntity subcategory) {
        this.subcategory = subcategory;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public CostEntity getCost() {
        return cost;
    }

    public void setCost(CostEntity cost) {
        this.cost = cost;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public List<CalendarEntity> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<CalendarEntity> calendars) {
        this.calendars = calendars;
    }

    public List<AppointmentEntity> getAppointPatients() {
        return appointPatients;
    }

    public void setAppointPatients(List<AppointmentEntity> appointPatients) {
        this.appointPatients = appointPatients;
    }

    public List<AppointmentEntity> getAppointMedicars() {
        return appointMedicars;
    }

    public void setAppointMedicars(List<AppointmentEntity> appointMedicars) {
        this.appointMedicars = appointMedicars;
    }

    public List<CommentEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<CommentEntity> patients) {
        this.patients = patients;
    }

    public List<CommentEntity> getMedicars() {
        return medicars;
    }

    public void setMedicars(List<CommentEntity> medicars) {
        this.medicars = medicars;
    }

	public RateEntity getRate() {
		return rate;
	}

	public void setRate(RateEntity rate) {
		this.rate = rate;
	}

	public List<SessionLogEntity> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionLogEntity> sessions) {
		this.sessions = sessions;
	}

    public List<RateTraceEntity> getRateTraces() {
        return rateTraces;
    }

    public void setRateTraces(List<RateTraceEntity> rateTraces) {
        this.rateTraces = rateTraces;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
    
}
