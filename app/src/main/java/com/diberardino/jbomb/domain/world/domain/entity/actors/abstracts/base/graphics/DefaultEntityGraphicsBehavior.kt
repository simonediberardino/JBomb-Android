package com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.graphics

import android.graphics.Bitmap
import android.util.Log
import com.diberardino.jbomb.JBombApplication
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.Entity
import com.diberardino.jbomb.domain.world.domain.entity.actors.abstracts.base.IEntityGraphicsBehavior
import com.diberardino.jbomb.utility.Utility
import com.diberardino.jbomb.utility.Utility.loadImage
import com.diberardino.jbomb.utility.now
import java.util.Locale
import java.util.regex.Pattern

abstract class DefaultEntityGraphicsBehavior : IEntityGraphicsBehavior {
    override fun loadAndSetImage(entity: Entity, imagePath: String): Bitmap? {
        if (entity.state.state == null)
            return doLoadAndSetImage(entity, imagePath)

        val toks = imagePath.split(Pattern.quote(".").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val extension = toks[1]
        val fileName = toks[0]
        val imagePathWithStatus = "${fileName}_${entity.state.state.toString().lowercase(Locale.getDefault())}.$extension"
        val hasImageWithStatus = Utility.fileExists(imagePathWithStatus)
        return if (hasImageWithStatus) doLoadAndSetImage(entity, imagePathWithStatus) else doLoadAndSetImage(entity, imagePath)
    }

    private fun doLoadAndSetImage(entity: Entity, imagePath: String): Bitmap? = try {
        entity.state.lastImageUpdate = now()

        loadImage(JBombApplication.context, imagePath).also {
            entity.image._image = it
            entity.image.imagePath = imagePath
        }
    } catch (exception: Exception) {
        Log.e(this.javaClass.simpleName, "Could not load image $imagePath")
        throw exception
    }
}