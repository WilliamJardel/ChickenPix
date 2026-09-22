import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import { Ionicons } from '@expo/vector-icons';

export default function CardSaldo({ saldo = "R$ 67,00", onPress }) {
  return (
    <TouchableOpacity 
      style={styles.container} 
      onPress={onPress}
      activeOpacity={0.7}
    >
      <View style={styles.infoArea}>
        <Text style={styles.titulo}>Saldo em conta</Text>
        <Text style={styles.valor}>{saldo}</Text>
      </View>

      <Ionicons name="chevron-forward" size={20} color="#222" />
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: '#FFFFFF',
    paddingHorizontal: 20,
    paddingVertical: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#F0F0F0',
  },
  infoArea: {
    flexDirection: 'column',
  },
  titulo: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#1A1A1A',
    marginBottom: 6,
  },
  valor: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#000000',
  },
});