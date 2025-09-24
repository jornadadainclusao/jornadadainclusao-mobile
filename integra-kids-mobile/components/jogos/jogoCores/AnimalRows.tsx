import React, { Key } from 'react'
import { View, Image } from 'react-native';

export function AnimalRows({ data, section, sectionsLen, elementsPerSection }: { data: any[], section: number, sectionsLen: number, elementsPerSection: number }) {
  const droppableWidth = 80;
  const padding = 20;

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

  const _data = data.map((value, index) => (
    { key: index + 1, id: index + 1, imgPath: value }
  ));
  let sectionedCores: Array<Array<object>> = [];
  for (let i = 0; i < sectionsLen; i++) {
    sectionedCores = [...sectionedCores, [...data.slice(i * elementsPerSection, (i + 1) * elementsPerSection)]];
  }

  return (
    <>
      {sectionedCores[section].map((value) => (
        <AnimalRow id={value.id} key={value.id} imgPath={value.imgPath}></AnimalRow>
      ))}
    </>
  );
}
