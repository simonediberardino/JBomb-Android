package com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.graphics

import androidx.compose.ui.graphics.ImageBitmap
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.EntityImageModel
import com.diberardino.jbomb.domain.world.domain.entity.actors.impl.explosion.abstractexpl.AbstractExplosion

class ExplosionImageModel(
    entity: Entity,
    entitiesAssetsPath: String = Entity.DEFAULT.ENTITIES_ASSETS_PATH,
    paddingTop: Int = Entity.DEFAULT.PADDING_TOP,
    paddingWidth: Int = Entity.DEFAULT.PADDING_WIDTH,
    imageRefreshRate: Int = AbstractExplosion.DEFAULT.IMAGE_REFRESH_RATE,
    _image: ImageBitmap? = Entity.DEFAULT.IMAGE,
    lastImageIndex: Int = Entity.DEFAULT.LAST_IMAGE_INDEX,
    lastImageUpdate: Long = Entity.DEFAULT.LAST_IMAGE_UPDATE,
    imagePath: String = Entity.DEFAULT.IMAGE_PATH,
) : EntityImageModel(
        entity = entity,
        entitiesAssetsPath = entitiesAssetsPath,
        paddingTop = paddingTop,
        paddingWidth = paddingWidth,
        imageRefreshRate = imageRefreshRate,
        _image = _image,
        lastImageIndex = lastImageIndex,
        lastImageUpdate = lastImageUpdate,
        imagePath = imagePath
) {
}