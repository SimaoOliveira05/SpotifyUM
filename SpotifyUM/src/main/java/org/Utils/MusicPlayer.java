package org.Utils;

import javax.sound.sampled.*;

import java.io.*;

/**
 *
 * Esta classe fornece métodos para reproduzir músicas armazenadas como arquivos WAV
 * no diretório de recursos da aplicação.
 */
public class MusicPlayer {
    /**
     * Reproduz um arquivo de áudio WAV com base no nome da música.
     *
     * O método procura o arquivo WAV correspondente ao nome fornecido no diretório de recursos,
     * cria um arquivo temporário para reprodução e utiliza a API javax.sound.sampled para tocar o áudio.
     *
     * @param musicName Nome da música (sem a extensão .wav).
     * @return O objeto {@link Clip} se a música foi reproduzida com sucesso, ou {@code null} caso contrário.
    */
    public Clip playMusic(String musicName){
        try {
            // Normaliza o nome da música para o formato do arquivo
            String fileName = musicName.trim().replace(" ", "_") + ".wav";
            String resourcePath = "Songs/" + fileName;
            
            // Obtém o stream do recurso
            InputStream inputStream = MusicPlayer.class.getClassLoader().getResourceAsStream(resourcePath);
            
            if (inputStream == null) {
                return null;
            }
            
            // Cria um arquivo temporário para o AudioSystem usar
            File tempFile = File.createTempFile("temp_audio_", ".wav");
            tempFile.deleteOnExit();
            
            // Copia o conteúdo do stream para o arquivo temporário
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            
            // Cria AudioInputStream a partir do arquivo temporário
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(tempFile);
            
            // Obtém e configura o Clip
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            
            
            // Reproduz o áudio
            clip.start();
            
            return clip;
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("❌ Erro ao reproduzir áudio: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}