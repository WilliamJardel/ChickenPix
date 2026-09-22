import { View, Text, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';
import Botao from '../src/components/Botao';

export default function Perfil() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Meu Perfil</Text>
      
      <Botao 
        titulo="Voltar" 
        cor="#FF3B30" 
        onPress={() => router.back()} 
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, justifyContent: 'center', alignItems: 'center' },
  titulo: { fontSize: 24, fontWeight: 'bold', marginBottom: 20 }
});