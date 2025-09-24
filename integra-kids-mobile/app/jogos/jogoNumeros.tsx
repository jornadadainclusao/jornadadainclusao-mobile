import React, { Children, Key, ReactNode, useEffect, useState } from 'react'
import { View, Text, Image, Button, Pressable } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { dificuldade } from '@/types/types'
import { transform } from '@babel/core';
import { randomizeArr } from '@/utils/utils';

export default function JogoNumeros({ dif = "easy" }: { dif: dificuldade }) {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];

  const [numeros, setNumeros] = useState(
    Array.from({ length: 10 }, (_, index) => ({
      id: index + 1,
      value: index + 1,
    }))
  );
  const numerosPerSection = 10;

  const [section, setSection] = useState(0)
  const sectionLen = Math.ceil(numeros.length / numerosPerSection);

  const droppableWidth = 80;
  const padding = 20;

  useEffect(() => {
    if (dif == "easy") return;

    const shuffledNumeros = randomizeArr(numeros);
    setNumeros(shuffledNumeros);
  }, [])

  const Numero = ({ id, numero }: { id: Key, numero: string }) => {
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

  const Numeros = ({ section }: { section: number }) => {
    let sectionedNumeros: Array<Array<object>> = [];
    for (let i = 0; i < sectionLen; i++) {
      sectionedNumeros = [...sectionedNumeros, [...numeros.slice(i * numerosPerSection, (i + 1) * numerosPerSection)]];
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
        {sectionedNumeros[section].map((numero) => (
          <Numero id={numero.id} key={numero.id} numero={numero.value - 1}></Numero>
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
        <Numeros section={section}></Numeros>
      </View>
      <ArrowSelection></ArrowSelection>
    </View>
  );
}
