package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.graphics

import android.graphics.Bitmap
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.character.Character

abstract class CharacterImageModel(
    entity: Entity,
    entitiesAssetsPath: String = Entity.DEFAULT.ENTITIES_ASSETS_PATH,
    paddingTop: Int = Entity.DEFAULT.PADDING_TOP,
    paddingWidth: Int = Entity.DEFAULT.PADDING_WIDTH,
    imageRefreshRate: Int = Entity.DEFAULT.IMAGE_REFRESH_RATE,
    _image: Bitmap? = Entity.DEFAULT.IMAGE,
    lastImageIndex: Int = Entity.DEFAULT.LAST_IMAGE_INDEX,
    lastImageUpdate: Long = Entity.DEFAULT.LAST_IMAGE_UPDATE,
    imagePath: String = Entity.DEFAULT.IMAGE_PATH
) : EntityImageModel(
        entity,
        entitiesAssetsPath,
        paddingTop,
        paddingWidth,
        imageRefreshRate,
        _image,
        lastImageIndex,
        lastImageUpdate,
        imagePath
) {
    abstract fun characterOrientedImages(): Array<String>
    override var hitboxSizeToHeightRatio: Float = Character.DEFAULT.HITBOX_SIZE_TO_HEIGHT_RATIO
}