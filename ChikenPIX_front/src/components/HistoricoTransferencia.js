import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { Ionicons } from '@expo/vector-icons';

export default function HistoricoTransferencia({ transacoes }) {
  // Lista de dados por defeito caso não sejam passados via props do Spring Boot
  const lista = transacoes || [
    { id: '1', nome: 'Rayn Gosling', hora: '15:55', tipo: 'Pix', valor: -12.00 },
    { id: '2', nome: 'Rayn Gosling', hora: '15:55', tipo: 'Pix', valor: 12.00 },
    { id: '3', nome: 'Sabrina Carpinteira', hora: '15:55', tipo: 'Pix', valor: 10.00 },
  ];

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Histórico de Transferência</Text>
      <Text style={styles.subtitulo}>Hoje</Text>

      {lista.map((item) => {
        const isEntrada = item.valor > 0;
        const valorFormatado = `${isEntrada ? '+ ' : '- '}R$ ${Math.abs(item.valor).toFixed(2).replace('.', ',')}`;

        return (
          <View key={item.id} style={styles.itemRow}>
            <View style={[styles.iconeCirculo, isEntrada ? styles.circuloEntrada : styles.circuloSaida]}>
              <Ionicons 
                name={isEntrada ? "arrow-down-outline" : "arrow-up-outline"} 
                size={20} 
                color={isEntrada ? "#00A88F" : "#333333"} 
              />
            </View>

            <View style={styles.infoArea}>
              <Text style={styles.nome}>{item.nome}</Text>
              <Text style={styles.detalhe}>{item.hora} - {item.tipo}</Text>
            </View>
            <Text style={[styles.valor, isEntrada ? styles.valorEntrada : styles.valorSaida]}>
              {valorFormatado}
            </Text>
          </View>
        );
      })}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#FFFFFF',
    paddingHorizontal: 20,
    paddingVertical: 16,
  },
  titulo: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#1A1A1A',
    marginBottom: 12,
  },
  subtitulo: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#333333',
    marginBottom: 16,
  },
  itemRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 18,
  },
  iconeCirculo: {
    width: 42,
    height: 42,
    borderRadius: 21,
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 12,
  },
  circuloEntrada: {
    backgroundColor: '#BCEBE3',
  },
  circuloSaida: {
    backgroundColor: '#E0E0E0', 
  },
  infoArea: {
    flex: 1,
  },
  nome: {
    fontSize: 15,
    fontWeight: 'bold',
    color: '#000000',
  },
  detalhe: {
    fontSize: 13,
    color: '#666666',
    marginTop: 2,
  },
  valor: {
    fontSize: 15,
    fontWeight: 'bold',
  },
  valorEntrada: {
    color: '#00A88F', 
  },
  valorSaida: {
    color: '#000000', 
  },
});