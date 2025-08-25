import { Link, Stack } from 'expo-router';
import { StyleSheet, Image, Text } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';

export default function NotFoundScreen() {
  const colorScheme = useColorScheme();
  const colors = Colors[colorScheme ?? 'light'];

  return (
    <>
      <Stack.Screen options={{ title: 'Oops!' }} />
      <ThemedView style={styles.container}>
        <Image
          source={require('../assets/images/404.png')}
          style={{
            width: 300,
            height: 300,
            resizeMode: 'contain',
            borderRadius: 999,
          }}
        />
        <Text
          style={{
            color: colors.text,
            fontSize: 40,
            fontWeight: 'bold',
            padding: 22,
            textAlign: 'center',
          }}
        >
          ERRO 404
        </Text>
       <Text
          style={{
            color: colors.text,
            fontSize: 20,
            fontWeight: 'bold',
            padding: 15,
            textAlign: 'center',
          }}
        >
          Ei colega, parece que voce se perdeu pelo mapa, sugerimos que retorne.
        </Text>
        <Text
          style={{
            color: colors.text,
            fontSize: 15,
            fontWeight: 'bold',
            textAlign: 'center',
          }}
        >
          Essa tela está em desenvolvimento ou não existe.
        </Text>
        

        <Link href="/(tabs)/jogos" style={styles.link}>
          <ThemedText type="link">Voltar ao inicio</ThemedText>
        </Link>
      </ThemedView>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
  },
  link: {
    marginTop: 15,
    paddingVertical: 15,
  },
});
