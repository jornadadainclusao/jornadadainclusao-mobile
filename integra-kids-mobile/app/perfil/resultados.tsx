import React, { useState } from 'react';
import { View, Text, StyleSheet, ScrollView, TouchableOpacity, FlatList, Switch } from 'react-native';
import { Picker } from '@react-native-picker/picker'; // para o select
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';

export default function Resultados() {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];

  // Estados
  const [pessoaSelecionada, setPessoaSelecionada] = useState('');
  const [graficoSelecionado, setGraficoSelecionado] = useState('barras');
  const [mostrarUltimas, setMostrarUltimas] = useState(true);

  // Dados fictícios para histórico
  const historicoPartidas = [
    { id: '1', jogo: 'Memória', resultado: '8/10 acertos', tempo: '1:32' },
    { id: '2', jogo: 'Cores', resultado: '100% acertos', tempo: '0:59' },
    { id: '3', jogo: 'Vogais', resultado: '7/10 acertos', tempo: '2:12' },
    { id: '4', jogo: 'Numeros', resultado: '6/10 acertos', tempo: '2:00' },
  ];

  return (
    <View style={[styles.container, { backgroundColor: colors.background }]}>
      {/* Select para escolher pessoa */}
      <Text style={[styles.label, { color: colors.text }]}>Escolha uma criança:</Text>
      <Picker
        selectedValue={pessoaSelecionada}
        onValueChange={(value) => setPessoaSelecionada(value)}
        dropdownIconColor={colors.text}
        style={[styles.picker, { color: colors.text }]}
      >
        <Picker.Item label="Selecione..." value="" />
        <Picker.Item label="João" value="joao" />
        <Picker.Item label="Maria" value="maria" />
      </Picker>

      {/* Espaço para gráfico */}
      <View style={[styles.graficoContainer, { borderColor: colors.tint }]}>
        <Text style={{ color: colors.text }}>
          {graficoSelecionado === 'barras' && 'Gráfico de Barras (placeholder)'}
          {graficoSelecionado === 'metrica' && 'Gráfico de Métrica (placeholder)'}
          {graficoSelecionado === 'radar' && 'Gráfico Radar (placeholder)'}
        </Text>
      </View>

      {/* Select para mudar gráfico */}
      <Text style={[styles.label, { color: colors.text }]}>Selecione o tipo de gráfico:</Text>
      <Picker
        selectedValue={graficoSelecionado}
        onValueChange={(value) => setGraficoSelecionado(value)}
        dropdownIconColor={colors.text}
        style={[styles.picker, { color: colors.text }]}
      >
        <Picker.Item label="Barras" value="barras" />
        <Picker.Item label="Métrica" value="metrica" />
        <Picker.Item label="Radar" value="radar" />
      </Picker>

      {/* Checkbox para mostrar últimas partidas */}
      <View style={styles.checkboxContainer}>
        <Text style={[styles.label, { color: colors.text}]}>Mostrar últimas partidas?</Text>
        <Switch
          value={mostrarUltimas}
          onValueChange={(value) => setMostrarUltimas(value)}
        />
      </View>

      {/* Histórico de partidas */}
            <Text style={[styles.label, { color: colors.text }]}>Histórico de partidas:</Text>
      {mostrarUltimas && (
        <ScrollView style={[styles.historicoContainer, { borderColor: colors.text }]}>
          {historicoPartidas.map((item) => (
            <View key={item.id} style={styles.partidaItem}>
              <Text style={{ color: colors.text }}>{item.jogo} - {item.resultado} - {item.tempo}</Text>
            </View>
          ))}
        </ScrollView>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  label: {
    fontSize: 18,
    marginBottom: 6,
    textAlign:'center',
    fontWeight:'bold',
  },
  picker: {
    borderWidth: 1,
    marginBottom: 12,
    borderColor:'white',
  },
  graficoContainer: {
    height: 300,
    borderWidth: 1,
    borderRadius: 18,
    justifyContent: 'center',
    alignItems: 'center',
    marginVertical: 12,
  },
  checkboxContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginVertical: 8,
    justifyContent: 'flex-end',
  },
  historicoContainer: {
    borderWidth: 1,
    borderRadius: 8,
    marginTop: 10,
    padding: 10,
    height: 'auto',
  },
  partidaItem: {
    paddingVertical: 4,
    borderBottomWidth: 0.5,
    borderBottomColor: '#888',
  },
});
