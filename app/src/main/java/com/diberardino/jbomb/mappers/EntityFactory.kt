package com.diberardino.jbomb.mappers

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.Clown
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.hat.Hat
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.ghost.GhostBoss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.clown_nose.ClownNose
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.eagle.Eagle
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.FastPurpleBall
import game.domain.world.types.EntityTypes

class EntityFactory {
    fun toEntity(entityTypes: EntityTypes, id: Long, extra:  Map<String, String>? = null): Entity? {
        return when (entityTypes) {
            EntityTypes.BomberEntity, EntityTypes.RemotePlayer, EntityTypes.Player -> createPlayerEntity(id, extra)
            EntityTypes.Clown -> Clown(null)
            EntityTypes.Hat -> Hat(id)
            EntityTypes.GhostBoss -> GhostBoss(id)
            EntityTypes.ClownNose -> ClownNose(id)
            EntityTypes.Eagle -> Eagle(id)
            EntityTypes.FastEnemy -> FastPurpleBall(id)
            EntityTypes.GhostEnemy -> GhostEnemy(id)
            EntityTypes.Helicopter -> Helicopter(id)
            EntityTypes.TankEnemy -> TankEnemy(id)
            EntityTypes.YellowBall -> YellowBall(id)
            EntityTypes.Skeleton -> SkeletonEnemy(id)
            EntityTypes.Zombie -> Zombie(id)
            EntityTypes.DestroyableBlock -> DestroyableBlock(id)
            EntityTypes.InvisibleBlock -> InvisibleBlock(id)
            EntityTypes.StoneBlock -> StoneBlock(id)
            EntityTypes.MysteryBoxPerk -> MysteryBoxPerk(id)
            EntityTypes.ConfettiExplosion, EntityTypes.FireExplosion, EntityTypes.PistolExplosion -> null
            EntityTypes.ArmorPowerUp -> ArmorPowerUp(id)
            EntityTypes.BlockMoverPowerUp -> BlockMoverPowerUp(id)
            EntityTypes.EmptyPowerup -> EmptyPowerup(id)
            EntityTypes.HammerPowerUp -> HammerPowerUp(id)
            EntityTypes.FirePowerUp -> FirePowerUp(id)
            EntityTypes.IncreaseMaxBombsPowerUp -> IncreaseMaxBombsPowerUp(id)
            EntityTypes.LivesPowerUp -> LivesPowerUp(id)
            EntityTypes.Fox -> FoxAnimal(id)
            EntityTypes.PistolPowerUp -> PistolPowerUp(id)
            EntityTypes.RemoteControlPowerUp -> RemoteControlPowerUp(id)
            EntityTypes.SpeedPowerUp -> SpeedPowerUp(id)
            EntityTypes.TransparentBombsPowerUp -> TransparentBombsPowerUp(id)
            EntityTypes.TransparentDestroyableBlocksPowerUp -> TransparentDestroyableBlocksPowerUp(id)
            EntityTypes.EndLevelPortal -> EndLevelPortal(id)
            EntityTypes.World1Portal -> World1Portal(id)
            EntityTypes.World2Portal -> World2Portal(id)
            else -> { null }
        }
    }

    private fun createPlayerEntity(id: Long, extra:  Map<String, String>? = null): Entity {
        val isServer = JBomb.match.isServer
        val isClient = JBomb.match.isClient

        return when {
            isServer && JBomb.match.player == null -> Player(id)
            isClient && id == (JBomb.match.onlineGameHandler as ClientGameHandler?)?.id -> Player(id)
            else -> RemotePlayer(null, id, extra?.getOrTrim("skinId")?.toInt() ?: 0)
        }
    }

    companion object {
        val instance: EntityFactory by lazy { EntityFactory() }
    }
}
