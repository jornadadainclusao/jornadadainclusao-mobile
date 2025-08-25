import { View, Text, TouchableOpacity } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { useState } from 'react';
import { useRouter } from 'expo-router';

export default function Jogos() {
  const colorScheme = useColorScheme();
  const colors = Colors[colorScheme ?? 'light'];
  const router = useRouter();
  const [expandedIndex, setExpandedIndex] = useState<number | null>(null);

  const buttons = [
    {
      text: 'Jogo das Cores',
      borderColor: '#FF4D4D',
      description: 'Aprenda e reconheça as cores de forma divertida.',
      route: '/jogos/jogo-das-cores',
    },
    {
      text: 'Jogo da Memória',
      borderColor: '#4DB6FF',
      description: 'Desafie sua memória e memore imagens rapidamente.',
      route: '/jogos/jogo-da-memoria',
    },
    {
      text: 'Jogo das Vogais',
      borderColor: '#4DFF88',
      description: 'Aprenda as vogais de forma divertida e interativa.',
      route: '/jogos/jogo-das-vogais',
    },
    {
      text: 'Jogo dos Números',
      borderColor: '#FFA64D',
      description: 'Treine contagem e números de forma divertida.',
      route: '/jogos/jogo-dos-numeros',
    },
  ];

  return (
    <View
      style={{
        flex: 1,
        backgroundColor: colors.background,
        padding: 16,
        justifyContent: 'center',
      }}
    >
      <Text style={{ color: colors.text,
                            fontSize: 40,
                            fontWeight: "bold",
                            padding: 22,
                            marginTop: 30,
                            textAlign: 'center',}}>Jogos</Text>
      {buttons.map((btn, index) => {
        const isExpanded = expandedIndex === index;
        return (
          <TouchableOpacity
            key={index}
            onPress={() => setExpandedIndex(isExpanded ? null : index)}
            style={{
              backgroundColor: colorScheme === 'dark' ? '#222' : '#eee',
              borderRadius: 12,
              borderWidth: 2,
              borderColor: btn.borderColor,
              marginBottom: 16,
              paddingVertical: 60, // mantém padding grande
              paddingHorizontal: 16,
              alignItems: 'center', // mantém centralizado
            }}
          >
            <Text style={{ color: colors.text, fontSize: 18, fontWeight: 'bold', textAlign: 'center' }}>
              {btn.text}
            </Text>

            {isExpanded && (
              <View style={{ marginTop: 16, alignItems: 'center' }}>
                <Text style={{ color: colors.text, fontSize: 14, marginBottom: 12, textAlign: 'center' }}>
                  {btn.description}
                </Text>
                <TouchableOpacity
                  onPress={() => router.push(btn.route)}
                  style={{
                    backgroundColor: btn.borderColor,
                    paddingVertical: 12,
                    paddingHorizontal: "40%",
                    borderRadius: 8,
                    marginTop:10,
                  }}
                >
                  <Text style={{ color: '#fff', fontSize: 16 }}>Jogar</Text>
                </TouchableOpacity>
              </View>
            )}
          </TouchableOpacity>
        );
      })}
    </View>
  );
}
