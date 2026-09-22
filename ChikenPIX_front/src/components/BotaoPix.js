import React from 'react';
import { View, StyleSheet, TouchableOpacity, Image } from 'react-native';
import { useRouter } from 'expo-router';

export default function BotaoPix({ onPixPress }) {
  const router = useRouter();

  const handlePress = () => {
    if (onPixPress) {
      onPixPress();
    } else {
      router.push('/opcoes-pix');
    }
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity 
        style={styles.floatingButtonOuter} 
        onPress={handlePress}
        activeOpacity={0.85}
      >
        <View style={styles.floatingButtonInner}>
          <Image 
            source={require('../../assets/images/pix.png')} 
            style={styles.pixIconNav} 
            resizeMode="contain"
          />
        </View>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    height: 50,
    backgroundColor: '#00A88F',
    alignItems: 'center',
    justifyContent: 'center',
  },
  floatingButtonOuter: {
    position: 'absolute',
    top: -26,
    width: 64,
    height: 64,
    borderRadius: 32,
    backgroundColor: '#FFFFFF',
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 6,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.2,
    shadowRadius: 5,
  },
  floatingButtonInner: {
    width: 50,
    height: 50,
    borderRadius: 25,
    backgroundColor: '#000000',
    justifyContent: 'center',
    alignItems: 'center',
  },
  pixIconNav: {
    width: 28,
    height: 28,
    tintColor: '#FFFFFF', 
  },
});