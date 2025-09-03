import { View, Text, TextInput, TouchableOpacity, Image } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { useState } from 'react';
import { Stack } from 'expo-router';

export default function EditCadastro() {
  const colorScheme = useColorScheme(); // 'light' ou 'dark'
  const colors = Colors[colorScheme ?? 'light'];

  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmarSenha, setConfirmarSenha] = useState('');

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
      <Stack.Screen options={{ title: 'Editar dados do responsável' }} />

      {/* Inputs */}
      <TextInput
        placeholder="Alterar nome"
        placeholderTextColor={colors.text + '80'}
        value={nome}
        onChangeText={setNome}
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
        placeholder="Alterar E-mail"
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
        placeholder="Alterar Senha"
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

      <TextInput
        placeholder="Confirme sua nova senha"
        placeholderTextColor={colors.text + '80'}
        secureTextEntry
        value={confirmarSenha}
        onChangeText={setConfirmarSenha}
        style={{
          width: '100%',
          padding: 12,
          borderWidth: 1,
          borderColor: colors.text,
          borderRadius: 10,
          marginBottom: 20,
          color: colors.text,
        }}
      />

      {/* Botão */}
      <TouchableOpacity
        onPress={() => console.log('altCad clicado')}
        style={{
          backgroundColor: colors.tint,
          width: "100%",
          paddingVertical: 12,
          paddingHorizontal: 24,
          borderRadius: 10,
        }}
      >
        <Text style={{ color: colors.background, fontSize: 16, textAlign: 'center' }}>Concluir alterações</Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={() => console.log('altCad clicado')}>
        <Text style={{ color: colors.tint, fontSize: 16, textAlign: 'center' }}>Excluir conta</Text>
      </TouchableOpacity>
    </View>
  );
}
