import { View, Text, TouchableOpacity } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { useRouter } from 'expo-router'; // Importando o hook useRouter

export default function Cadlog() {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];
  const router = useRouter(); // Inicializando o router para navegação

  // Funções de navegação
  const handleLoginPress = () => {
    router.push('/login'); // Redireciona para a rota de login
  };

  const handleRegisterPress = () => {
    router.push('/cadastro'); // Redireciona para a rota de cadastro
  };

  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: colors.background, // fundo adaptável
        headerShown: false,

      }}
    >
      <Text style={{ color: colors.text, fontSize: 20 }}>Cadastre-se ou faça seu Login</Text>
      
      {/* Botão para Login */}
      <TouchableOpacity onPress={handleLoginPress}>
        <Text style={{ color: colors.tint, fontSize: 25, marginTop: 20, backgroundColor: colors.background }}>Fazer Login</Text>
      </TouchableOpacity>

      {/* Botão para Cadastro */}
      <TouchableOpacity onPress={handleRegisterPress}>
        <Text style={{ color: colors.tint, fontSize: 25, marginTop: 20 }}>Cadastrar-se</Text>
      </TouchableOpacity>
    </View>
  );
}
