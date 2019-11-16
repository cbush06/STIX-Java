package com.stephenott.stix

import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.objects.core.StixCoreObject
import com.stephenott.stix.objects.core.sco.objects.IPv6Address
import com.stephenott.stix.objects.core.sco.objects.NetworkTraffic
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sdo.objects.AttackPattern
import com.stephenott.stix.objects.core.sdo.objects.AttackPatternSdo
import com.stephenott.stix.objects.core.sro.StixRelationshipObject
import com.stephenott.stix.objects.core.sro.objects.Relationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.objects.core.sro.objects.Sighting
import com.stephenott.stix.objects.meta.datamarking.MarkingDefinition
import com.stephenott.stix.objects.meta.datamarking.objects.Statement
import com.stephenott.stix.objects.meta.datamarking.objects.Tlp
import com.stephenott.stix.serialization.json.StixJsonContentMapper
import com.stephenott.stix.serialization.json.toJson
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.MarkingDefinitionTypeOv

object MainRunner {

    @JvmStatic
    fun main(args: Array<String>){

        val ap1 = AttackPattern(name = "124", confidence = StixConfidence(33))
        val ap2 = AttackPattern("1245")

        val sighting1 = Sighting(sightingOfRef = ap1.id)
        val net1 = NetworkTraffic(isActive = StixBoolean(true), protocols = StixStringList(listOf("http")))

        val mapper = StixJsonContentMapper(StixRegistries())

        println(ap1.toJson(mapper))
        println(mapper.parseJson<AttackPattern>(ap1.toJson(mapper)))
        println(mapper.parseJson<AttackPatternSdo>(ap1.toJson(mapper)))
        println(mapper.parseJson<StixDomainObject>(ap1.toJson(mapper)))
        println(mapper.parseJson<StixCoreObject>(ap1.toJson(mapper)))
        println(mapper.parseJson<StixObject>(ap1.toJson(mapper)))
        println(mapper.parseJson<StixContent>(ap1.toJson(mapper)))
//
//        println(net1.toJson(mapper))
//        println(mapper.parseJson<NetworkTraffic>(net1.toJson(mapper)))
//
//        val jsonString: String = ap1.toJson(mapper)
//        println(jsonString)
//
//        println(mapper.parseJson<StixContent>(jsonString))
//        println(mapper.parseJson<StixObject>(jsonString))
//        println(mapper.parseJson<StixCoreObject>(jsonString))
//        println(mapper.parseJson<StixDomainObject>(jsonString))
//        println(mapper.parseJson<AttackPatternSdo>(jsonString))
//        println(mapper.parseJson<AttackPattern>(jsonString))
//
//
//        val rel = Relationship(
//            relationshipType = RelationshipType("duplicate-of"),
//            sourceRef = ap1,
//            targetRef = ap2
//        )
//        println(mapper.parseJson<StixContent>(rel.toJson(mapper)))
//        println(mapper.parseJson<StixObject>(rel.toJson(mapper)))
//        println(mapper.parseJson<StixCoreObject>(rel.toJson(mapper)))
//        println(mapper.parseJson<StixRelationshipObject>(rel.toJson(mapper)))
//        println(mapper.parseJson<RelationshipSro>(rel.toJson(mapper)))
//        println(mapper.parseJson<Relationship>(rel.toJson(mapper)))
//
//        println(mapper.parseJson<Sighting>(sighting1.toJson(mapper)))
//
//        val markDef1 = MarkingDefinition("test", MarkingDefinitionTypeOv("tlp"),
//            Tlp("white")
//        )
//        println(mapper.parseJson<MarkingDefinition>(markDef1.toJson(mapper)))
//        println(markDef1.toJson(mapper))
//
//        val markDef2 = MarkingDefinition("test2", MarkingDefinitionTypeOv("statement"),
//            Statement("white-statement")
//        )
//        println(mapper.parseJson<MarkingDefinition>(markDef2.toJson(mapper)))
//        println(markDef2.toJson(mapper))
    }
}