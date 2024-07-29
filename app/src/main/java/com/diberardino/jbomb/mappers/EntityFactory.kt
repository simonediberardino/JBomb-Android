package com.diberardino.jbomb.mappers

import com.diberardino.jbomb.JBomb
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.animals.FoxAnimal
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.destroyable_block.DestroyableBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.invisible_block.InvisibleBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.blocks.stone_block.StoneBlock
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.player.Player
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bomber_entity.remote_player.RemotePlayer
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.bonus.mystery_box.MysteryBoxPerk
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.Clown
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.clown.hat.Hat
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.boss.ghost.GhostBoss
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.clown_nose.ClownNose
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.eagle.Eagle
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.fast_enemy.FastPurpleBall
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.ghost_enemy.GhostEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.helicopter.Helicopter
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.skeleton.SkeletonEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.tank.TankEnemy
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.yellow_ball.YellowBall
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.enemies.npcs.zombie.Zombie
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.EndLevelPortal
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.World2Portal
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.ArmorPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.BlockMoverPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.EmptyPowerup
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.FirePowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.HammerPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.IncreaseMaxBombsPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.LivesPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.RemoteControlPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.SpeedPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.TransparentBombsPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.powerups.TransparentDestroyableBlocksPowerUp
import com.diberardino.jbomb.domain.world.domain.entity.pickups.portals.World1Portal
import com.diberardino.jbomb.domain.world.domain.pickups.powerups.PistolPowerUp
import com.diberardino.jbomb.domain.world.types.EntityTypes
import com.diberardino.jbomb.network.gamehandler.ClientGameHandler
import com.diberardino.jbomb.utility.Extensions.getOrTrim

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
