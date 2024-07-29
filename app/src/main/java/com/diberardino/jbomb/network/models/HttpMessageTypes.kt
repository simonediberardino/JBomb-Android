package com.diberardino.jbomb.network.models

enum class HttpMessageTypes {
    LOCATION,
    PLAYER_JOIN_REQUEST,
    SPAWNED_ENTITY,
    DESPAWNED_ENTITY,
    NEW_LEVEL,
    ASSIGN_ID,
    FIRE,
    USE_ITEM,
    ENTITY_ATTACKED,
    UPDATE_INFO,
    LEVEL_INFO,
    ENEMIES_COUNT,
    ENTITY_COLLIDED,
    BOMB_EXPLODED
}