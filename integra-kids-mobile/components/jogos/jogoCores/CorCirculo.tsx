import React, { Key } from 'react'
import { View, } from 'react-native';

export function CorCirculo({ id, cor }: { id: Key, cor: string }) {
  const droppableWidth = 80;

  return (
    <View
      style={{
        backgroundColor: String(cor),
        borderRadius: "50%",
        borderColor: "#000000",
        borderWidth: 1,
        width: droppableWidth,
        height: droppableWidth,
        margin: 0
      }}
      key={id}
    >
    </View>
  )
}
