import React, { Key } from 'react'
import { View, Text } from 'react-native';
import { Letra } from './Letra';

export function Letras(
  { data, section, sectionsLen, elementsPerSection }:
    { data: any[], section: number, sectionsLen: number, elementsPerSection: number }
) {
  const padding = 20;

  let sectionedLetras: Array<Array<object>> = [];
  for (let i = 0; i < sectionsLen; i++) {
    sectionedLetras = [...sectionedLetras, [...data.slice(i * elementsPerSection, (i + 1) * elementsPerSection)]];
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
