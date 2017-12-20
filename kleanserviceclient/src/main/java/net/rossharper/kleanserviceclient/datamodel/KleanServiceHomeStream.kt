package net.rossharper.kleanserviceclient.datamodel

data class KleanServiceHomeStream(val view: KleanServiceDataView)
data class KleanServiceDataView(val sections: List<KleanServiceSection>)
data class KleanServiceSection(val id: String, val title: String, val entities: List<Entity>)
abstract class Entity
data class ShowEntity(val id: String, val title: String) : Entity()
