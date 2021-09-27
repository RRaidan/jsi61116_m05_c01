package id.ac.unand.fti.si.pbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {
    
    @Test
    public void testClassAvailable(){
        try {
            Class<?> transaksiClass = Class.forName("id.ac.unand.fti.si.pbo.Transaksi");			
            transaksiClass.getMethod("hitungTotalBayar");
            
            Class<?> pelangganClass = Class.forName("id.ac.unand.fti.si.pbo.Pelanggan");
            pelangganClass.getField("member");

            Class<?> barangClass = Class.forName("id.ac.unand.fti.si.pbo.Barang");
            barangClass.getField("hargaJual");
            
        }catch(NoSuchMethodException e) {
            assertTrue(false, "Method hitungTotalBayar tidak ditemukan pada kelas Transaksi");
        }catch(NoSuchFieldException e){
            assertTrue(false, "Property member tidak ditemukan pada kelas Pelanggan atau property hargaJual tidak ditemukan pada kelas Barang");
        }catch(ClassNotFoundException e) {
            assertTrue(false, "Kelas Transaksi, Barang atau Pelanggan tidak ditemukan");
        }  
    }
    
    @Test
    public void testReadOnlyNoRef()
    {
        try {
            Class<?> transaksiClass = Class.forName("id.ac.unand.fti.si.pbo.Transaksi");			
            transaksiClass.getMethod("hitungTotalBayar");
            transaksiClass.getMethod("getNoRef");
            transaksiClass.getMethod("getTanggal");
        }catch(NoSuchMethodException e) {
            assertTrue(false, "Method hitungTotalBayar, getNoref atau getTanggal tidak ditemukan pada kelas Transaksi");
        }catch(ClassNotFoundException e) {
            assertTrue(false, "Kelas Transaksi, Barang atau Pelanggan tidak ditemukan");
        }  

        try {
            Class<?> transaksiClass = Class.forName("id.ac.unand.fti.si.pbo.Transaksi");	
            Class<?>[] parameters = {String.class};		
            transaksiClass.getMethod("setTanggal", parameters);
            assertTrue(false, "Methode setTanggal seharusnya tidak ada");
            transaksiClass.getField("tanggal");
            assertTrue(false, "Field tanggal seharusnya tidak disexpose");
        }catch(NoSuchMethodException e) {
            assertTrue(true);
        }catch(ClassNotFoundException e) {
            assertTrue(false, "Kelas Transaksi, Barang atau Pelanggan tidak ditemukan");
        } catch (NoSuchFieldException e) {
            assertTrue(true);
        }  
        try {
            Class<?> transaksiClass = Class.forName("id.ac.unand.fti.si.pbo.Transaksi");	
            Class<?>[] parameters = {String.class};		
            transaksiClass.getMethod("setNoRef", parameters);
            assertTrue(false, "Method setNoRef seharusnya tidak ada");
            transaksiClass.getField("noref");
            assertTrue(false, "Field noRef seharusnya tidak disexpose");
        }catch(NoSuchMethodException e) {
            assertTrue(true);
        }catch(ClassNotFoundException e) {
            assertTrue(false, "Kelas Transaksi, Barang atau Pelanggan tidak ditemukan");
        } catch (NoSuchFieldException e) {
            assertTrue(true);
        }  
    }
    
    @Test
    public void testMethodHitungBayarMemberIsCorrect()
    {
        try {
            Class<?> transaksiClass = Class.forName("id.ac.unand.fti.si.pbo.Transaksi");			
            transaksiClass.getMethod("hitungTotalBayar");
            
            Pelanggan pelanggan = new Pelanggan();
            pelanggan.member = true;
            
            Barang barang = new Barang();
            barang.hargaJual = 250000;
            Transaksi transaksi = new Transaksi(pelanggan);
            transaksi.barang = barang;
            
            transaksi.jumlahBarang = 1;
            double totalBayar = transaksi.hitungTotalBayar();
            assertEquals(250000, totalBayar);
            
            transaksi.jumlahBarang = 3;
            totalBayar = transaksi.hitungTotalBayar();
            assertEquals(742500, totalBayar);
            
            transaksi.jumlahBarang = 10;
            totalBayar = transaksi.hitungTotalBayar();
            assertEquals(2425000, totalBayar);
            
            transaksi.jumlahBarang = 100;
            totalBayar = transaksi.hitungTotalBayar();
            assertEquals(23750000, totalBayar);
            
            
        }catch(NoSuchMethodException e) {
            assertTrue(false, "Method hitungTotalBayar tidak ditemukan");
        }catch(ClassNotFoundException e) {
            assertTrue(false, "Kelas Transaksi tidak ditemukan");
        }  
    }
    
    
    
}
