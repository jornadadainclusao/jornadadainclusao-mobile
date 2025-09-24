// não puxa dados da api ainda

import React from 'react';
import { Dimensions } from 'react-native';
import { ProgressChart } from 'react-native-chart-kit';

const screenWidth = Dimensions.get('window').width - 60;

const hexToRgba = (hex, opacity = 1) => {
  const cleanHex = hex.replace('#', '');
  const bigint = parseInt(cleanHex, 16);
  const r = (bigint >> 16) & 255;
  const g = (bigint >> 8) & 255;
  const b = bigint & 255;
  return `rgba(${r},${g},${b},${opacity})`;
};

const ProgressChartComponent = ({ data, themeColors }) => {
  // Define se é tema escuro ou claro com base na cor de background
  const isDark = themeColors.background === '#151718'; // seu dark background

  return (
    <ProgressChart
      data={data}
      width={screenWidth}
      height={220}
      strokeWidth={16}
      radius={32}
      chartConfig={{
        backgroundGradientFrom: isDark ? themeColors.background : '#fff',
        backgroundGradientTo: isDark ? themeColors.background : '#fff',
        color: (opacity = 1) => hexToRgba(themeColors.tint, opacity),
        labelColor: (opacity = 1) => hexToRgba(themeColors.text, opacity),
        propsForLabels: {
          fill: themeColors.text,
        },
      }}
      style={{ borderRadius: 8 }}
    />
  );
};


export default ProgressChartComponent;
