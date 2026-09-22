import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Image } from 'react-native';
import { Ionicons } from '@expo/vector-icons';

export default function AcessoRapido({ onPix, onCofre, onChaves }) {
  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Acesso Rápido</Text>

      <View style={styles.botoesRow}>
        <TouchableOpacity style={styles.item} onPress={onPix} activeOpacity={0.7}>
          <View style={styles.circulo}>
         <Image 
            source={require('../../assets/images/pix.png')} 
            style={styles.pixIcon} 
            resizeMode="contain"
         />
          </View>
          <Text style={styles.label}>Pix</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.item} onPress={onCofre} activeOpacity={0.7}>
          <View style={styles.circulo}>
            <Ionicons name="business-outline" size={30} color="#000000" />
          </View>
          <Text style={styles.label}>Cofre</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.item} onPress={onChaves} activeOpacity={0.7}>
          <View style={styles.circulo}>
            <Ionicons name="key-outline" size={30} color="#000000" />
          </View>
          <Text style={styles.label}>Chaves</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#FFFFFF',
    paddingHorizontal: 20,
    paddingVertical: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#F0F0F0',
  },
  titulo: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#1A1A1A',
    marginBottom: 16,
  },
  botoesRow: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
  },
  item: {
    alignItems: 'center',
  },
  circulo: {
    width: 72,
    height: 72,
    borderRadius: 36,
    backgroundColor: '#BCEBE3',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 8,
  },
  pixIcon: {
    width: 36,
    height: 36,
  },
  label: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#000000',
  },
});