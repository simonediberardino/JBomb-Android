    package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb

    import com.diberardino.jbomb.audio.AudioManager
    import com.diberardino.jbomb.audio.SoundModel
    import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
    import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityImageModel
    import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityProperties
    import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
    import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics.PeriodicGraphicsBehavior
    import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.hard_block.HardBlock
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.base.BomberEntity
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.models.Explosive
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.base.PlaceableEntity
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.base.state.PlaceableEntityState
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.logic.BombLogic
    import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.placeable.bomb.state.BombState
    import com.diberardino.jbomb.domain.world.domain.entity.geo.Coordinates
    import com.diberardino.jbomb.domain.world.domain.geo.Coordinates
    import com.diberardino.jbomb.domain.world.types.EntityTypes
    import com.diberardino.jbomb.utils.file_system.Paths

    /**
     * Represents a bomb entity that can explode and cause damage in the game.
     *
     * @param caller The entity that spawned the bomb.
     */
    open class Bomb(caller: BomberEntity) : PlaceableEntity(), Explosive {
        constructor(id: Long, caller: BomberEntity) : this(caller) {
            info.id = id
        }

        constructor(coordinates: Coordinates?, caller: BomberEntity) : this(caller) {
            coordinates?.let {
                info.position = it
            }
        }

        override val state: PlaceableEntityState = BombState(entity = this, caller = caller)

        init {
            info.position = Coordinates.getCenterCoordinatesOfEntity(state.caller)
        }

        override val image: EntityImageModel = EntityImageModel(
                entity = this,
                entitiesAssetsPath = "${Paths.entitiesFolder}/bomb/bomb%format%.png"
        )

        override val logic: BombLogic = BombLogic(entity = this)
        override val properties: EntityProperties = EntityProperties(
                type = EntityTypes.Bomb
        )

        override val graphicsBehavior: IEntityGraphicsBehavior = object : PeriodicGraphicsBehavior() {
            override val imagesCount: Int
                get() = 3
            override val allowUiState: Boolean
                get() = false

            override fun onImageChanged(entity: Entity) {
                super.onImageChanged(entity)
                AudioManager.instance.play(SoundModel.BOMB_CLOCK)
            }
        }

        /**
         * Gets the maximum explosion distance for the bomb.
         *
         * @return The maximum explosion distance.
         */
        override val maxExplosionDistance: Int
            get() = (state.caller as BomberEntity).state.currExplosionLength

        override val explosionInteractionEntities: Set<Class<out Entity>> = Bomb.DEFAULT.EXPLOSION_INTERACTION_ENTITIES
        override val explosionObstacles: Set<Class<out Entity>> = Bomb.DEFAULT.EXPLOSION_OBSTACLES

        /**
         * Companion object containing constant values for the Bomb class.
         */
        companion object {
            const val PLACE_INTERVAL: Long = 1000
            const val EXPLODE_TIMER = 5000
        }

        internal object DEFAULT {
            val SIZE = DEFAULT_SIZE

            val EXPLOSION_OBSTACLES: Set<Class<out Entity>>
                get() = setOf(HardBlock::class.java, )

            val EXPLOSION_INTERACTION_ENTITIES: Set<Class<out Entity>>
                get() = setOf(DestroyableBlock::class.java, Character::class.java, Bomb::class.java)

            val INTERACTION_ENTITIES: MutableSet<Class<out Entity>>
                get() = hashSetOf(AbstractExplosion::class.java)
        }
    }

