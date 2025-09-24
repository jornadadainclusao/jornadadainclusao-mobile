import { View, Text, TextInput, TouchableOpacity, Image, ScrollView, Platform } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';
import { useState } from 'react';
import { Stack } from 'expo-router';
import { Picker } from '@react-native-picker/picker';
import DateTimePicker from '@react-native-community/datetimepicker';
import insereDependenteBd from '@/services/api'

export default function AddCrianca() {
  const colorScheme = useColorScheme();
  const colors = Colors[colorScheme ?? 'light'];

  // Estados
  const [nome, setNome] = useState('');
  const [dataNascimento, setDataNascimento] = useState<Date | null>(null);
  const [genero, setGenero] = useState<'masculino' | 'feminino' | ''>('');
  const [selectedIndex, setSelectedIndex] = useState<number | null>(null);
  const [showDatePicker, setShowDatePicker] = useState(false);

  // Avatares
  const imagens = [
    require('@/assets/images/playerIcons/avatar-person-pilot.png'),
    require('@/assets/images/playerIcons/fighter-luchador-man.png'),
    require('@/assets/images/playerIcons/avatar-cloud-crying.png'),
    require('@/assets/images/playerIcons/avatar-female-portrait-2.png'),
    require('@/assets/images/playerIcons/avatar-child-girl.png'),
    require('@/assets/images/playerIcons/anime-away-face.png'),
    require('@/assets/images/playerIcons/avatar-avocado-food.png'),
    require('@/assets/images/playerIcons/afro-avatar-male.png'),
    require('@/assets/images/playerIcons/avatar-batman-comics.png'),
    require('@/assets/images/playerIcons/avatar-cacti-cactus.png'),
    require('@/assets/images/playerIcons/avatar-joker-squad.png'),
    require('@/assets/images/playerIcons/avatar-einstein-professor.png'),
  ];

  // Calcular idade para validar
  const calcularIdade = (date: Date) => {
    const hoje = new Date();
    let idade = hoje.getFullYear() - date.getFullYear();
    const mes = hoje.getMonth() - date.getMonth();
    if (mes < 0 || (mes === 0 && hoje.getDate() < date.getDate())) {
      idade--;
    }
    return idade;
  };

  const cadastrarDependente = async () => {

    const objDependente = {
      nome: nome,
      idade: dataNascimento,
      sexo: genero,
      foto: imagens[selectedIndex],
      usuario_id_fk: {
        id: 1
      }
    }
    try {
      const response = await insereDependenteBd(objDependente);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const json = await response.json();
      console.log(json);
      
    } catch (e) {
      console.log(e);
    } finally {
      console.log('deu bom');
    }

  }

  return (
    <ScrollView style={{ flex: 1, backgroundColor: colors.background }}>
      <View
        style={{
          flex: 1,
          justifyContent: 'center',
          alignItems: 'center',
          padding: 40,
          gap: 20,
        }}
      >
        <Stack.Screen options={{ title: 'Cadastrar uma criança' }} />

        <Text style={{ color: colors.text }}>
          Escolha um avatar:
        </Text>

        {/* Grid de imagens */}
        <View
          style={{
            flexDirection: 'row',
            flexWrap: 'wrap',
            justifyContent: 'space-between',
            width: '100%',
          }}
        >
          {imagens.map((img, index) => (
            <TouchableOpacity
              key={index}
              style={{
                width: '25%',
                aspectRatio: 1,
                marginBottom: 20,
                marginHorizontal: 10,
                borderWidth: 3,
                borderColor:
                  selectedIndex === index ? colors.tint : colors.text + '50',
                borderRadius: 999,
                overflow: 'hidden',
              }}
              onPress={() => setSelectedIndex(index)}
            >
              <Image
                source={img}
                style={{ width: '100%', height: '100%', resizeMode: 'cover' }}
              />
            </TouchableOpacity>
          ))}
        </View>

        {/* Nome */}
        <TextInput
          placeholder="Inserir nome"
          placeholderTextColor={colors.text + '80'}
          value={nome}
          onChangeText={setNome}
          style={{
            width: '100%',
            padding: 12,
            borderWidth: 1,
            borderColor: colors.text,
            borderRadius: 10,
            color: colors.text,
          }}
        />

        {/* Data de nascimento */}
        <TouchableOpacity
          onPress={() => setShowDatePicker(true)}
          style={{
            width: '100%',
            padding: 12,
            borderWidth: 1,
            borderColor: colors.text,
            borderRadius: 10,
          }}
        >
          <Text style={{ color: dataNascimento ? colors.text : colors.text + '80' }}>
            {dataNascimento
              ? `Nascimento: ${dataNascimento.toLocaleDateString()} (${calcularIdade(
                dataNascimento
              )} anos)`
              : 'Insira a data de nascimento (3 a 10 anos)'}
          </Text>
        </TouchableOpacity>
        {showDatePicker && (
          <DateTimePicker
            value={dataNascimento || new Date()}
            mode="date"
            display={Platform.OS === 'ios' ? 'spinner' : 'default'}
            onChange={(event, selectedDate) => {
              setShowDatePicker(false);
              if (selectedDate) {
                const idade = calcularIdade(selectedDate);
                if (idade < 3 || idade > 10) {
                  alert('A criança deve ter entre 3 e 10 anos.');
                  return;
                }
                setDataNascimento(selectedDate);
              }
            }}
          />
        )}

        {/* Picker de Gênero */}
        <View
          style={{
            width: '100%',
            borderWidth: 1,
            borderColor: colors.text,
            borderRadius: 10,
          }}
        >
          <Picker style={{ color: colors.text, }} selectedValue={genero} onValueChange={(val) => setGenero(val)} dropdownIconColor={colors.text}>
            <Picker.Item label="Selecione o gênero" value="" enabled={false} />
            <Picker.Item label="Masculino" value="masculino" />
            <Picker.Item label="Feminino" value="feminino" />
          </Picker>
        </View>

        {/* Botões */}
        <TouchableOpacity
          onPress={() => cadastrarDependente}
          style={{
            backgroundColor: colors.tint,
            width: '100%',
            paddingVertical: 12,
            paddingHorizontal: 24,
            borderRadius: 10,
          }}
        >
          <Text style={{ color: colors.background, fontSize: 16, textAlign: 'center' }}>
            Cadastrar criança
          </Text>
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
}
