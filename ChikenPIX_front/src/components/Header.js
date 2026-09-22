import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import { Ionicons } from '@expo/vector-icons';

export default function Header({ nome = "Pedro", onPressPerfil, onPressMenu }) {
  return (
    <View style={styles.headerContainer}>
      <View style={styles.topRow}>
        <TouchableOpacity 
          style={styles.avatarCircle} 
          onPress={onPressPerfil}
          activeOpacity={0.8}
        >
          <Ionicons name="person-outline" size={24} color="#222" />
        </TouchableOpacity>

        <TouchableOpacity onPress={onPressMenu} activeOpacity={0.7}>
          <Ionicons name="menu-outline" size={34} color="#FFF" />
        </TouchableOpacity>
      </View>
      <Text style={styles.saudacao}>Olá, {nome}</Text>
      
    </View>
  );
}

const styles = StyleSheet.create({
  headerContainer: {
    backgroundColor: '#00A88F',
    paddingHorizontal: 20,
    paddingTop: 16,
    paddingBottom: 24,
  },
  topRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
  },
  avatarCircle: {
    width: 48,
    height: 48,
    borderRadius: 24,
    backgroundColor: '#BCEBE3', 
    justifyContent: 'center',
    alignItems: 'center',
  },
  saudacao: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#FFFFFF',
  },
});