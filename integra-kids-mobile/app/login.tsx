import { View, Text, TextInput, TouchableOpacity, Image } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { useState } from 'react';
import { Stack } from 'expo-router';

import Checkbox from 'expo-checkbox';

export default function Login() {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];

  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmarSenha, setConfirmarSenha] = useState('');
  const [manterLogado, setManterLogado] = useState(false);

  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        gap: 20,
        alignItems: 'center',
        backgroundColor: colors.background,
        padding: 40,
      }}
    >
      <Stack.Screen options={{ title: 'Realize seu Login' }} />

      <Image
        source={require('../assets/images/LOGO.png')}
        style={{
          width: "80%",
          height: 80,
          resizeMode: 'contain',
        }}
      />

      <Text style={{ color: colors.text, fontSize: 20, textAlign: "center", marginBottom: 30 }}>Bem-vindo de volta! Faça seu login para continuar. </Text>


      <TextInput
        placeholder="Digite seu E-mail"
        placeholderTextColor={colors.text + '80'}
        value={email}
        onChangeText={setEmail}
        style={{
          width: '100%',
          padding: 12,
          borderWidth: 1,
          borderColor: colors.text,
          borderRadius: 10,
          marginBottom: 12,
          color: colors.text,
        }}
      />

      <TextInput
        placeholder="Digite sua Senha"
        placeholderTextColor={colors.text + '80'}
        secureTextEntry
        value={senha}
        onChangeText={setSenha}
        style={{
          width: '100%',
          padding: 12,
          borderWidth: 1,
          borderColor: colors.text,
          borderRadius: 10,
          marginBottom: 12,
          color: colors.text,
        }}
      />


      {/* Botão */}
      <TouchableOpacity
        onPress={() => console.log('Logar clicado')}
        style={{
          backgroundColor: colors.tint,
          width: "100%",
          paddingVertical: 12,
          paddingHorizontal: 24,
          borderRadius: 10,
        }}
      >
        <Text style={{ color: colors.background, fontSize: 16, textAlign: 'center' }}>Entrar</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => console.log('esqueci a senha clicado')}
        style={{
          width: "100%",
          borderRadius: 10,
        }}>
        <Text style={{ color: 'blue', fontSize: 16, textAlign: 'center' }}>Esqueci a senha</Text>
      </TouchableOpacity>

      <View
        style={{
          flexDirection: 'row',
          alignItems: 'center',
          marginBottom: 20,
        }}
      >
        <Checkbox
          value={manterLogado}
          onValueChange={setManterLogado}
          color={manterLogado ? "red" : undefined}
        />
        <Text style={{ marginLeft: 8, color: colors.text }}>
          Manter Logado?
        </Text>
      </View>


    </View>
  );
}
