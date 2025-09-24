import React, { Key, ReactNode, useEffect, useState } from 'react'
import { View, Text, Image, Button, Pressable } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { dificuldade } from '@/types/types'
import { transform } from '@babel/core';
import { randomizeArr } from '@/utils/utils';

export default function JogoCores({ dif = "easy" }: { dif: dificuldade }) {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];
  const [cores, setCores] = useState(["#E9E9E9", "#FA70E4", "#8FF43F", "#FAA94B", "#FFE96A", "#EB4A4A"]);
  const [images, setImages] = useState([
    require("@/assets/images/jogoCores/borboleta.png"),
    require("@/assets/images/jogoCores/coelho.png"),
    require("@/assets/images/jogoCores/joaninha.png"),
    require("@/assets/images/jogoCores/leao.png"),
    require("@/assets/images/jogoCores/peixinho.png"),
    require("@/assets/images/jogoCores/sapo.png"),
  ]);

  const [section, setSection] = useState(0)
  const animalsPerSection = 3;
  const sectionLen = Math.floor(images.length / animalsPerSection);

  const droppableWidth = 80;
  const padding = 20;

  useEffect(() => {
    const shuffledCores = randomizeArr([...cores]);
    const shuffledImages = randomizeArr([...images]);

    setCores(shuffledCores);
    setImages(shuffledImages);
  }, []);

  const CorCirculo = ({ id, cor }: { id: Key, cor: string }) => {
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

  const AnimalRows = ({ section }: { section: number }) => {
    const AnimalRow = ({ id, imgPath }: { id: Key, imgPath: any }) => (
      <View
        style={{
          backgroundColor: "#D9D9D9",
          borderRadius: 8,
          padding: padding,
          flexDirection: "row",
          justifyContent: "space-between"
        }}
        key={id}>
        <Image
          style={{
            width: droppableWidth,
            height: droppableWidth,
          }}
          source={imgPath}>
        </Image>
        <View style={{
          backgroundColor: "#7D7D7D",
          borderColor: "#000000",
          borderRadius: "50%",
          height: droppableWidth,
          width: droppableWidth,
        }}>
        </View>
      </View>
    )

    const data = images.map((value, index) => (
      { key: index + 1, id: index + 1, imgPath: value }
    ));
    let sectionedCores: Array<Array<object>> = [];
    for (let i = 0; i < sectionLen; i++) {
      sectionedCores = [...sectionedCores, [...data.slice(i * animalsPerSection, (i + 1) * animalsPerSection)]];
    }

    return (
      <>
        {sectionedCores[section].map((value) => (
          <AnimalRow id={value.id} key={value.id} imgPath={value.imgPath}></AnimalRow>
        ))}
      </>
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
          padding: padding,
        }}>
        {cores.map((value, index) => (
          <CorCirculo key={index + 1} id={index + 1} cor={value}></CorCirculo>
        ))}
      </View>
      <View
        style={{
          gap: 10
        }}>
        <AnimalRows section={section}></AnimalRows>
      </View>
      <ArrowSelection></ArrowSelection>
    </View>
  );
}
