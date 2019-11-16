package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.StixRegistries
import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.objects.DomainNameSco
import com.stephenott.stix.objects.core.sco.objects.IPv4AddressSco
import com.stephenott.stix.objects.core.sco.objects.IPv6AddressSco
import com.stephenott.stix.objects.core.sco.objects.UrlSco
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.InfrastructureTypes
import com.stephenott.stix.type.KillChainPhases

interface InfrastructureSdo : StixDomainObject {
    val name: String
    val description: String?
    val infrastructureTypes: InfrastructureTypes
    val aliases: StixStringList?
    val killChainPhases: KillChainPhases?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant?

    companion object : CompanionStixType,
        BusinessRulesValidator<InfrastructureSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("infrastructure")

        override fun objectValidationRules(obj: InfrastructureSdo, stixRegistries: StixRegistries) {
            requireStixType(this.stixType, obj)

            if (obj.firstSeen != null && obj.lastSeen != null){
                require(obj.lastSeen!!.instant >= obj.firstSeen!!.instant)
            }
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("communicates-with"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("communicates-with"),
                IPv4AddressSco::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("communicates-with"),
                IPv6AddressSco::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("communicates-with"),
                DomainNameSco::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("communicates-with"),
                UrlSco::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("consists-of"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("consists-of"),
                ObservedDataSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("consists-of"),
                StixCyberObservableObject::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("controls"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("controls"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("delivers"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("has"),
                VulnerabilitySdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("hosts"),
                ToolSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("hosts"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("located-at"),
                LocationSdo::class
            ),
            AllowedRelationship(
                InfrastructureSdo::class,
                RelationshipType("uses"),
                InfrastructureSdo::class
            )
        )
    }

}

data class Infrastructure(
    override val name: String,
    override val description: String? = null,
    override val infrastructureTypes: InfrastructureTypes,
    override val aliases: StixStringList? = null,
    override val killChainPhases: KillChainPhases? = null,
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
    override val type: StixType = InfrastructureSdo.stixType,
    override val id: StixIdentifier = StixIdentifier(type),
    override val createdByRef: String? = null,
    override val created: StixInstant = StixInstant(),
    override val externalReferences: ExternalReferences? = null,
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(),
    override val labels: StixLabels? = null,
    override val modified: StixInstant = StixInstant(created),
    override val revoked: StixBoolean = StixBoolean(),
    override val confidence: StixConfidence? = null,
    override val lang: StixLang? = null,
    override val stixRegistries: StixRegistries = Stix.defaultRegistries
) : InfrastructureSdo {

    init {
        InfrastructureSdo.objectValidationRules(this, stixRegistries)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return InfrastructureSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}