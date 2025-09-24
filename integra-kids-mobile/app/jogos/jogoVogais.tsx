import React, { Children, Key, ReactNode, useEffect, useState } from 'react'
import { View, Text, Image, Button, Pressable } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { dificuldade } from '@/types/types'
import { transform } from '@babel/core';
import { randomizeArr } from '@/utils/utils';

export default function JogoVogais({ dif = "easy" }: { dif: dificuldade }) {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];

  const [letras, setLetras] = useState(
    Array.from({ length: 26 }, (_, index) => ({
      id: index + 1,
      value: index + 1,
    }))
  );
  const letrasPerSection = 12;

  const [section, setSection] = useState(0)
  const sectionLen = Math.ceil(letras.length / letrasPerSection);

  const droppableWidth = 80;
  const padding = 20;

  useEffect(() => {
    if (dif == "easy") return;

    const shuffledLetras = randomizeArr(letras);
    setLetras(shuffledLetras);
  }, [])

  const Letra = ({ id, letra }: { id: Key, letra: string }) => {
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
        }}>{letra}</Text>
      </View>
    )
  }

  const Letras = ({ section }: { section: number }) => {
    let sectionedLetras: Array<Array<object>> = [];
    for (let i = 0; i < sectionLen; i++) {
      sectionedLetras = [...sectionedLetras, [...letras.slice(i * letrasPerSection, (i + 1) * letrasPerSection)]];
    }

    return (
      <View
        style={{
          borderRadius: 8,
          padding: padding,
          flexDirection: "row",
          flexWrap: "wrap",
          justifyContent: "space-between",
          columnGap: 4,
          rowGap: 32
        }}
        key={section + 1}>
        {sectionedLetras[section].map((letra) => (
          <Letra id={letra.id} key={letra.id} letra={String.fromCharCode(64 + letra.value)}></Letra>
        ))}
      </View>
    );
  }

  const ArrowSelection = () => {
    const handlePress = (acrescimo: number) => {
      setSection(Math.abs(section + acrescimo) % sectionLen);
    };

    const PressableImage = ({ style, acrescimo }: { style: object, acrescimo: number }) => {
      return (
        <Pressable onPress={() => handlePress(acrescimo)} onLongPress={() => { }}>
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

  return (
    <View
      style={{
        justifyContent: "space-between",
        height: "100%",
        padding: padding,
        backgroundColor: colors.background, // fundo adaptável
      }}
    >
      <View
        style={{
          backgroundColor: "#D9D9D9",
          borderRadius: 8,
          columnGap: 40,
          rowGap: 20,
          flexDirection: "row",
          flexWrap: "wrap",
          alignContent: "space-around",
          justifyContent: "space-around",
          minHeight: "20%",
          padding: padding,
        }}>
      </View>
      <View
        style={{
          gap: 10
        }}>
        <Letras section={section}></Letras>
      </View>
      <ArrowSelection></ArrowSelection>
    </View>
  );
}
