import { View, Text, Image, ScrollView } from 'react-native';
import { useColorScheme } from '@/hooks/useColorScheme';
import { Colors } from '@/constants/Colors';

export default function Sobre() {
    const colorScheme = useColorScheme();
    const colors = Colors[colorScheme ?? 'light'];

    // Conteúdo de exemplo
    const sections = [
        {
            image: require('../../assets/images/sobre/sobre1.jpeg'), // troque pelo caminho da imagem
            text: 'A plataforma "Integra Kids" foi desenvolvida com o objetivo de apoiar crianças do ensino fundamental que enfrentam dificuldades em matérias específicas. Com um ambiente lúdico e interativo, a plataforma oferece uma variedade de games educativos que tornam o aprendizado mais divertido e acessível. Ao identificar as áreas em que o aluno precisa de mais ajuda, a plataforma adapta as atividades, proporcionando um suporte personalizado que visa aumentar a confiança e a compreensão da criança.',
        },
        {
            image: require('../../assets/images/sobre/sobre2.jpeg'),
            text: 'Os jogos disponíveis na Integra Kids abrangem disciplinas, como: matemática, ciências e língua portuguesa. Cada jogo é projetado para ser divertido e desafiador, promovendo o engajamento dos alunos enquanto trabalham suas habilidades. A plataforma também conta com um sistema de feedback, que permite que os educadores e pais acompanhem o progresso da criança, identificando as dificuldades específicas e proporcionando conquistas ao longo do caminho.',
        },
        {
            image: require('../../assets/images/sobre/sobre3.jpeg'),
            text: 'Além disso, a Integra Kids oferece recomendações de games complementares baseados no desempenho do aluno. Quando uma criança encontra dificuldades em um conceito, a plataforma sugere atividades alternativas que reforçam o aprendizado de forma lúdica. Dessa maneira, a criança é redirecionada a games que atendem suas necessidades, facilitando a compreensão e promovendo um aprendizado mais eficaz e divertido. Com essa abordagem, a Integra Kids se torna uma ferramenta valiosa tanto para alunos quanto para educadores, contribuindo para um aprendizado significativo e divertido.',
        },
        {
            image: require('../../assets/images/sobre/sobre4.png'),
            text: 'O personagem ilustrado é o símbolo central do projeto "Integra Kids". Com sua coroa dourada, capa vermelha e lápis gigante, ele representa o espírito aventureiro e criativo que a plataforma busca despertar nas crianças. Além de inspirar confiança e curiosidade, o mascote desempenha um papel essencial na conexão com o público infantil, tornando o aprendizado mais acessível e divertido. Sua presença lúdica e acolhedora ajuda a criar uma relação de proximidade, incentivando as crianças a explorar novas ideias e superar desafios de forma leve e engajante.',
        },
    ];

    return (
        <ScrollView style={{ flex: 1, backgroundColor: colors.background, padding: 16 }}>
            <Text style={{ color: colors.text,
                            fontSize: 40,
                            fontWeight: "bold",
                            padding: 22,
                            marginTop: 30,
                            textAlign: 'center',}}>Nosso App</Text>
            {sections.map((section, index) => (
                <View key={index} style={{ marginBottom: 24, alignItems: 'center', padding: 16 }}>
                    <Image
                        source={section.image}
                        style={{ width: '100%', height: 350, resizeMode: 'cover', borderRadius: 12 }}
                    />
                    <Text
                        style={{
                            color: colors.text,
                            fontSize: 16,
                            marginTop: 22,
                            textAlign: 'justify',
                        }}
                    >
                        {section.text}
                    </Text>
                </View>
            ))}
        </ScrollView>
    );
}
