import { View, Text } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';

export default function Login() {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];

  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: colors.background, // fundo adaptável
      }}
    >
      <Text style={{ color: colors.text, fontSize: 20 }}>Tela de Login</Text>
    </View>
  );
}