import React, { Children, Key, ReactNode, useEffect, useState } from 'react'
import { View, Text, Image, Button, Pressable } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { dificuldade } from '@/types/types'
import { transform } from '@babel/core';
import { randomizeArr } from '@/utils/utils';

export default function JogoMemoria({ dif = "easy" }: { dif: dificuldade }) {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];

  const [cards, setCards] = useState(
    Array.from({ length: 10 }, (_, index) => ({
      id: index + 1,
      value: index + 1,
    }))
  );
  const cardsPerRound = 10;
  const [round, setRound] = useState(0)

  const droppableWidth = 80;
  const padding = 20;

  useEffect(() => {
    const shuffledCards = randomizeArr(cards);
    setCards(shuffledCards);
  }, [])

  const Card = ({ id, numero }: { id: Key, numero: string }) => {
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

  const Cards = ({ section }: { section: number }) => {
    let roundCards: Array<Array<object>> = [];

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
      </View>
    );
  }

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
        <Cards section={round}></Cards>
      </View>
    </View>
  );
}
