package com.accounting.radrest.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad nativa de FreeRADIUS para el registro de contabilidad de sesiones de red (AAA).
 * Alineado con la sección 7.6 de la especificación de arquitectura.
 */

@Data
@Entity
@Table(name = "radacct", indexes = {
    @Index(name = "radacct_active_session_idx", columnList = "acctuniqueid") // Índice condicional en BD (WHERE acctstoptime IS NULL)
})
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
    private String username;

    @Column(name = "realm", columnDefinition = "text")
    private String realm;

    @Column(name = "nasipaddress", nullable = false, columnDefinition = "inet")
    private String nasIpAddress;

    @Column(name = "nasportid", columnDefinition = "text")
    private String nasPortId;

    @Column(name = "nasporttype", columnDefinition = "text")
    private String nasPortType;

    @Column(name = "acctstarttime", columnDefinition = "timestamp with time zone")
    private OffsetDateTime acctStartTime;

    @Column(name = "acctupdatetime", columnDefinition = "timestamp with time zone")
    private OffsetDateTime acctUpdateTime;

    @Column(name = "acctstoptime", columnDefinition = "timestamp with time zone")
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

    @Column(name = "framedipaddress", columnDefinition = "inet")
    private String framedIpAddress;

    @Column(name = "framedipv6address", columnDefinition = "inet")
    private String framedIpv6Address;

    @Column(name = "framedipv6prefix", columnDefinition = "inet")
    private String framedIpv6Prefix;

    @Column(name = "framedinterfaceid", columnDefinition = "text")
    private String framedInterfaceId;

    @Column(name = "delegatedipv6prefix", columnDefinition = "inet")
    private String delegatedIpv6Prefix;

    @Column(name = "class", columnDefinition = "text")
    private String clazz; // 'class' es palabra reservada en Java
}