package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.StixRegistries
import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.KillChainPhases
import com.stephenott.stix.type.vocab.ToolTypes

interface ToolSdo : StixDomainObject {
    val name: String
    val description: String?
    val toolTypes: ToolTypes
    val aliases: StixStringList?
    val killChainPhases: KillChainPhases?
    val toolVersion: String?

    companion object : CompanionStixType,
        BusinessRulesValidator<ToolSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("tool")

        override fun objectValidationRules(obj: ToolSdo, stixRegistries: StixRegistries) {
            requireStixType(this.stixType, obj)
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("delivers"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("drops"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("has"),
                VulnerabilitySdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("targets"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("targets"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("targets"),
                LocationSdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("targets"),
                VulnerabilitySdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("uses"),
                InfrastructureSdo::class
            )
        )
    }
}

data class Tool(
    override val name: String,
    override val description: String? = null,
    override val toolTypes: ToolTypes,
    override val aliases: StixStringList? = null,
    override val killChainPhases: KillChainPhases? = null,
    override val toolVersion: String? = null,
    override val type: StixType = ToolSdo.stixType,
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
) : ToolSdo {

    init {
        ToolSdo.objectValidationRules(this, stixRegistries)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return ToolSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}