import React, { Key } from 'react'
import { View, Text } from 'react-native';

export function Numero({ id, numero }: { id: Key, numero: string }) {
  const droppableWidth = 80;

  return (
    <View
      style={{
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        backgroundColor: "#D9D9D9",
        borderRadius: 8,
        width: droppableWidth,
        height: droppableWidth,
        margin: 0
      }}
      key={id}
    >
      <Text style={{
        fontSize: 50,
        margin: 0,
        textAlign: "center",
      }}>{numero}</Text>
    </View>
  )
}

