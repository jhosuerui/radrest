package com.accounting.radrest.model;

import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.OffsetDateTime;

@Entity
@Table(name = "radacct", indexes = {
    @Index(name = "radacct_active_session_idx", columnList = "acctuniqueid"),
    @Index(name = "radacct_bulk_close", columnList = "nasipaddress, acctstarttime"),
    @Index(name = "radacct_start_user_idx", columnList = "acctstarttime, username")
})
@Getter
@Setter
public class RadAcct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "radacctid")
    private Long radAcctId;

    @Column(name = "acctsessionid", nullable = false, columnDefinition = "text")
    private String acctSessionId;

    @Column(name = "acctuniqueid", nullable = false, unique = true, columnDefinition = "text")
    private String acctUniqueId;

    @Column(name = "username", columnDefinition = "text")
    private String userName;

    @Column(name = "realm", columnDefinition = "text")
    private String realm;

    @JdbcTypeCode(SqlTypes.INET)
    @Column(name = "nasipaddress", nullable = false)
    private String nasIpAddress;

    @Column(name = "nasportid", columnDefinition = "text")
    private String nasPortId;

    @Column(name = "nasporttype", columnDefinition = "text")
    private String nasPortType;

    @Column(name = "acctstarttime")
    private OffsetDateTime acctStartTime;

    @Column(name = "acctupdatetime")
    private OffsetDateTime acctUpdateTime;

    @Column(name = "acctstoptime")
    private OffsetDateTime acctStopTime;

    @Column(name = "acctinterval")
    private Long acctInterval;

    @Column(name = "acctsessiontime")
    private Long acctSessionTime;

    @Column(name = "acctauthentic", columnDefinition = "text")
    private String acctAuthentic;

    @Column(name = "connectinfo_start", columnDefinition = "text")
    private String connectInfoStart;

    @Column(name = "connectinfo_stop", columnDefinition = "text")
    private String connectInfoStop;

    @Column(name = "acctinputoctets")
    private Long acctInputOctets;

    @Column(name = "acctoutputoctets")
    private Long acctOutputOctets;

    @Column(name = "calledstationid", columnDefinition = "text")
    private String calledStationId;

    @Column(name = "callingstationid", columnDefinition = "text")
    private String callingStationId;

    @Column(name = "acctterminatecause", columnDefinition = "text")
    private String acctTerminateCause;

    @Column(name = "servicetype", columnDefinition = "text")
    private String serviceType;

    @Column(name = "framedprotocol", columnDefinition = "text")
    private String framedProtocol;

    @JdbcTypeCode(SqlTypes.INET)
    @Column(name = "framedipaddress")
    private String framedIpAddress;

    @JdbcTypeCode(SqlTypes.INET)
    @Column(name = "framedipv6address")
    private String framedIpv6Address;

    @JdbcTypeCode(SqlTypes.INET)
    @Column(name = "framedipv6prefix")
    private String framedIpv6Prefix;

    @Column(name = "framedinterfaceid", columnDefinition = "text")
    private String framedInterfaceId;

    @JdbcTypeCode(SqlTypes.INET)
    @Column(name = "delegatedipv6prefix")
    private String delegatedIpv6Prefix;

    @Column(name = "class", columnDefinition = "text")
    private String clazz;
}
