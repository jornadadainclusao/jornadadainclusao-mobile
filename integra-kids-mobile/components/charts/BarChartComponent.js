// não puxa dados da api ainda

import React from 'react';
import { Dimensions } from 'react-native';
import { BarChart } from 'react-native-chart-kit';

const screenWidth = Dimensions.get('window').width - 15;

// Função para converter HEX para RGBA com opacidade
const hexToRgba = (hex, opacity = 1) => {
  const cleanHex = hex.replace('#', '');
  const bigint = parseInt(cleanHex, 16);
  const r = (bigint >> 16) & 255;
  const g = (bigint >> 8) & 255;
  const b = bigint & 255;
  return `rgba(${r},${g},${b},${opacity})`;
};

const BarChartComponent = ({ data, themeColors }) => {
  // Detecta se o tema é escuro para ajustar o fundo do gráfico
  const isDark = themeColors.background === '#151718'; // seu background dark

  return (
    <BarChart
      data={data}
      width={screenWidth - 32}
      height={220}
      fromZero
      chartConfig={{
        backgroundGradientFrom: isDark ? themeColors.background : '#fff',
        backgroundGradientTo: isDark ? themeColors.background : '#fff',
        decimalPlaces: 0,
        color: (opacity = 1) => hexToRgba(themeColors.tint, opacity),       // cor das barras
        labelColor: (opacity = 1) => hexToRgba(themeColors.text, opacity),   // cor dos labels
        barPercentage: 0.5,
        style: {
          borderRadius: 8,
        },
        propsForBackgroundLines: {
          strokeDasharray: '', // linha sólida
          stroke: isDark
            ? 'rgba(255, 255, 255, 0.2)' // linhas claras no escuro
            : 'rgba(0, 0, 0, 0.1)',      // linhas escuras no claro
        },
      }}
      verticalLabelRotation={30}
      style={{ borderRadius: 8 }}
    />
  );
};

export default BarChartComponent;
