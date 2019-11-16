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
import com.stephenott.stix.type.vocab.GroupingContextOv

interface GroupingSdo : StixDomainObject {
    val name: String?
    val description: String?
    val context: GroupingContextOv
    val objectRefs: StixIdentifiers

    companion object : CompanionStixType,
        BusinessRulesValidator<GroupingSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("grouping")

        override fun objectValidationRules(obj: GroupingSdo, stixRegistries: StixRegistries) {
            requireStixType(this.stixType, obj)
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf()
    }
}

data class Grouping(
    override val name: String? = null,
    override val description: String? = null,
    override val context: GroupingContextOv,
    override val objectRefs: StixIdentifiers,
    override val type: StixType = GroupingSdo.stixType,
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
) : GroupingSdo {

    init {
        GroupingSdo.objectValidationRules(this, stixRegistries)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return GroupingSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}