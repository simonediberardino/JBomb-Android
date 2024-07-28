package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics

import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import game.utils.Utility.fileExists
import game.utils.Utility.loadImage
import game.utils.dev.Log
import game.utils.time.now
import java.awt.image.Bitmap
import java.util.*
import java.util.regex.Pattern

abstract class DefaultEntityGraphicsBehavior : IEntityGraphicsBehavior {
    override fun loadAndSetImage(entity: Entity, imagePath: String): Bitmap? {
        if (entity.state.state == null)
            return doLoadAndSetImage(entity, imagePath)

        val toks = imagePath.split(Pattern.quote(".").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val extension = toks[1]
        val fileName = toks[0]
        val imagePathWithStatus = "${fileName}_${entity.state.state.toString().lowercase(Locale.getDefault())}.$extension"
        val hasImageWithStatus = fileExists(imagePathWithStatus)
        return if (hasImageWithStatus) doLoadAndSetImage(entity, imagePathWithStatus) else doLoadAndSetImage(entity, imagePath)
    }

    private fun doLoadAndSetImage(entity: Entity, imagePath: String): Bitmap? {
        entity.state.lastImageUpdate = now()

        return try {
            loadImage(imagePath)?.let {
                entity.image._image = loadImage(imagePath)
                entity.image.imagePath = imagePath
                return entity.image._image
            }
        } catch (exception: Exception) {
            Log.e("Could not load image $imagePath")
            throw exception
        }
    }
}