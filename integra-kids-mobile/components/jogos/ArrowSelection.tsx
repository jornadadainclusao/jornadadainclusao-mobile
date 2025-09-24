import React from 'react'
import { View, Image, Pressable } from 'react-native';

export const ArrowSelection = ({ handlePress }: { handlePress: Function }) => {
  const PressableImage = ({ style, acrescimo }: { style: object, acrescimo: number }) => {
    return (
      <Pressable onPress={() => handlePress()} onLongPress={() => { }}>
        <Image style={style} source={require("../../assets/images/arrow.png")}></Image>
      </Pressable>
    )
  };

  return (
    <View style={{
      flexDirection: "row",
      justifyContent: "space-around",
    }}>
      <PressableImage acrescimo={(-1)} style={{ transform: "rotate(180deg)" }}></PressableImage>
      <PressableImage acrescimo={1} style={{}}></PressableImage>
    </View>
  )
};
