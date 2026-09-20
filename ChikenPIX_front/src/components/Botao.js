import { TouchableOpacity, Text, StyleSheet } from 'react-native';

export default function Botao({ titulo, onPress, cor = '#007AFF' }) {
  return (
    <TouchableOpacity 
      style={[styles.botao, { backgroundColor: cor }]} 
      onPress={onPress}
      activeOpacity={0.8}
    >
      <Text style={styles.texto}>{titulo}</Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  botao: {
    paddingVertical: 15,
    paddingHorizontal: 20,
    borderRadius: 8,
    alignItems: 'center',
    justifyContent: 'center',
    width: '100%',
    marginVertical: 10,
  },
  texto: {
    color: '#FFF',
    fontSize: 16,
    fontWeight: 'bold',
  }
});