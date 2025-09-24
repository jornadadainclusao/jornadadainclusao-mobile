import React, { Key } from 'react'
import { View, Text } from 'react-native';
import { Numero } from './Numero';

const Numeros = ({ data, section, sectionsLen, elementsPerSection }: { data: any[], section: number, sectionsLen: number, elementsPerSection: number }) => {
  const padding = 20;

  let sectionedNumeros: Array<Array<object>> = [];
  for (let i = 0; i < sectionsLen; i++) {
    sectionedNumeros = [...sectionedNumeros, [...data.slice(i * elementsPerSection, (i + 1) * elementsPerSection)]];
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
