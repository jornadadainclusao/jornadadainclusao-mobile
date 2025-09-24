// Esse grafico ainda será ajustado, pois está errado e tá quebrando o App, favor evitar ele por enquanto

// components/RadarChartComponent.js
import React from 'react';
import { Dimensions } from 'react-native';
import { LineChart } from 'react-native-chart-kit';

const screenWidth = Dimensions.get('window').width -10;

const RadarChartComponent = ({ data }) => {
  return (
    <LineChart
      data={data}
      width={screenWidth - 32}
      height={220}
      chartConfig={{
        backgroundGradientFrom: '#fff',
        backgroundGradientTo: '#fff',
        decimalPlaces: 1,
        color: (opacity = 1) => `rgba(0, 123, 255, ${opacity})`,
        labelColor: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
      }}
      bezier
      style={{ borderRadius: 8 }}
    />
  );
};

export default RadarChartComponent;
