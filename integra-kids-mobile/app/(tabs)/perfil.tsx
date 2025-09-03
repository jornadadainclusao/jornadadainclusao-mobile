import { View, Text, TouchableOpacity } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { useRouter } from 'expo-router';

export default function Perfil() {
  const colorScheme = useColorScheme();
  const colors = Colors[colorScheme ?? 'light'];
  const router = useRouter();

  // Configuração dos botões com a rota de destino
  const buttons = [
    { text: 'Editar Cadastro', borderColor: '#FF4D4D', route: '../perfil/editCadastro' },
    { text: 'Adicionar Criança', borderColor: '#4DB6FF', route: '../perfil/addCrianca' },
    { text: 'Editar Criança', borderColor: '#4DFF88', route: '../perfil/editCrianca' },
    { text: 'Mudar Jogador', borderColor: '#FFA64D', route: '../perfil/mudarPlayer' },
    { text: 'Resultados', borderColor: '#D14DFF', route: '../perfil/resultados' },
    { text: 'Fazer Logout', borderColor: '#FF4D96', route: '../logCad' },
  ];

  const handlePress = (route: string) => {
    router.push(route); // navega para a tela correspondente
  };

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
                            textAlign: 'center',}}>Perfil</Text>

      <View style={{ flexDirection: 'row', flexWrap: 'wrap', justifyContent: 'space-between' }}>
        {buttons.map((btn, index) => (
          <TouchableOpacity
            key={index}
            onPress={() => handlePress(btn.route)}
            style={{
              width: '48%', // duas colunas
              height: 200,
              backgroundColor: colorScheme === 'dark' ? '#222' : '#eee',
              borderRadius: 12,
              borderWidth: 3,
              borderColor: btn.borderColor,
              justifyContent: 'center',
              alignItems: 'center',
              marginBottom: 16,
            }}
          >
            <Text style={{ color: colors.text, fontSize: 25, padding: 20, fontWeight: "bold", textAlign: "center"}}>{btn.text}</Text>
          </TouchableOpacity>
        ))}
      </View>
    </View>
  );
}
