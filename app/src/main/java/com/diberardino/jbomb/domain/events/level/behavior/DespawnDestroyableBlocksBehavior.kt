package com.diberardino.jbomb.domain.events.level.behavior

class DespawnDestroyableBlocksBehavior : GameBehavior() {
    override fun hostBehavior(): () -> Unit {
        return {
            despawnDestroyableBlocks()
        }
    }

    override fun clientBehavior(): () -> Unit {
        return {}
    }

    private fun despawnDestroyableBlocks() {
        /*JBomb.match
                .getEntities()
                .stream()
                .filter { entity: Entity? -> entity is DestroyableBlock }
                .forEach { obj: Entity -> obj.logic.eliminated() }*/
    }

}
