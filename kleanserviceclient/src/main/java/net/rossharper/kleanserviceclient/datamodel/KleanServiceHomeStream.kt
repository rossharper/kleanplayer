package net.rossharper.kleanserviceclient.datamodel

data class KleanServiceHomeStream(val view: KleanServiceDataView)
data class KleanServiceDataView(val sections: List<KleanServiceSection>)
data class KleanServiceSection(val id: String, val title: String, val entities: List<KleanServiceEntity>)
abstract class KleanServiceEntity
data class KleanServiceShowEntity(val id: String, val title: String) : KleanServiceEntity()
