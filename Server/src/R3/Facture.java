package R3;


        import com.itextpdf.kernel.color.Color;
        import com.itextpdf.kernel.font.PdfFont;
        import com.itextpdf.kernel.font.PdfFontFactory;
        import com.itextpdf.kernel.pdf.PdfDocument;
        import com.itextpdf.kernel.pdf.PdfWriter;
        import com.itextpdf.layout.Document;
        import com.itextpdf.layout.border.Border;
        import com.itextpdf.layout.element.*;
        import com.itextpdf.layout.property.TextAlignment;
        import com.itextpdf.layout.property.UnitValue;
        import model.Redevance;
        import org.apache.log4j.Logger;
        import utils.Constants;
        import utils.daoUtils.BoutiqueDAO;
        import utils.daoUtils.RedevanceDAO;

        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.text.SimpleDateFormat;

        import static sun.security.pkcs11.wrapper.Constants.NEWLINE;

public class Facture {

    public static final String DEST = "C:\\Users\\remys\\Documents/facture.pdf";
    public static final String BOLD = "Client/src/res/OpenSans-Bold.ttf";
    private String dest;
    Logger logger;
    private BoutiqueDAO bDAO;
    private RedevanceDAO rDAO;


    public Facture(BoutiqueDAO bDAO, RedevanceDAO rDAO){
        this.bDAO = bDAO;
        this.rDAO = rDAO;
        logger = Logger.getLogger(Algorithm.class);
    }

    public String createPdf(String destination, int id_redevance) throws IOException{

            Redevance redevance;
            redevance=rDAO.findFromReference(id_redevance).get(0);

        FileOutputStream fos = new FileOutputStream("C:\\wamp64\\www\\pdf\\"+destination);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setFontSize(10);

        PdfFont bold = PdfFontFactory.createFont(BOLD, true);

        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 65),
                new UnitValue(UnitValue.PERCENT, 35)})
                .setWidthPercent(100);

        String npng= "Soci\u00e9t\u00e9 NoPainNoGain";
        String adresse="1 rue des travailleurs";
        String codepostal="75000 Paris";
        String telephone="01 67 82 98 23";

        Paragraph vide= new Paragraph();

        Paragraph p = new Paragraph();
        p.setMultipliedLeading(0.7f);
        p.add(new Text(npng).setFont(bold));p.add(NEWLINE);
        p.add(adresse);p.add(NEWLINE);
        p.add(codepostal);p.add(NEWLINE);
        p.add(telephone);p.add(NEWLINE);


        String client= redevance.getNom_boutique();
        String telclient="01 23 56 78 87";


        Paragraph p1 = new Paragraph();

        p1.setMultipliedLeading(0.7f);
        p1.add(new Text(client).setFont(bold));p1.add(NEWLINE);
        p1.add(telclient);p1.add(NEWLINE);

        // String ch = "Bonjour"; String chInverse = ""; int chLg = ch.length(); // longueur de ch System.out.println("\nch : " + ch); for(int i = chLg -1; i>-1; i--) { chInverse+= ch.substring(i, i +1); } System.out.println("chInverse: " + chInverse);

        Paragraph p2 = new Paragraph();
        p2.setMultipliedLeading(0.7f);
        p2.add(new Text("FACTURE N\u00B020185090").setFont(bold));p1.add(NEWLINE);

        SimpleDateFormat formater = new SimpleDateFormat("MMMMM yyyy");
        String titre= "Redevance "+redevance.getNom_boutique()+" du mois de "+ formater.format(redevance.getDate_redevance());
        Paragraph p3 = new Paragraph();
        p3.setMultipliedLeading(0.7f);
        p3.add(titre);p1.add(NEWLINE);

        SimpleDateFormat formater1 = new SimpleDateFormat("dd MMMMM yyyy");
        Paragraph p4 = new Paragraph();
        p4.setMultipliedLeading(0.7f);
        p4.add(new Text("R\u00e9alis\u00e9e le "+formater1.format(redevance.getDate_redevance())).setFont(bold));p1.add(NEWLINE);


        Cell NPNG = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(p);

        Cell Vide = new Cell()
                .setBorder((Border.NO_BORDER))
                .add(vide);

        Cell CLIENT = new Cell()
                .setBorder((Border.NO_BORDER))
                .add(p1);

        Cell FACTURE = new Cell()
                .setBorder((Border.NO_BORDER))
                .add(p2);

        Cell TITRE = new Cell()
                .setBorder((Border.NO_BORDER))
                .add(p3);
        Cell DATE = new Cell()
                .setBorder((Border.NO_BORDER))
                .add(p4);


        table.addCell(NPNG);
        table.addCell(Vide);
        table.addCell(Vide);
        table.addCell(CLIENT);
        table.addCell(FACTURE);
        table.addCell(Vide);
        table.addCell(TITRE);
        table.addCell(DATE);



        Paragraph qte=new Paragraph(new Text("Quantit\u00e9").setFont(bold));
        Paragraph design=new Paragraph(new Text("D\u00e9signation").setFont(bold));
        Paragraph prixuni=new Paragraph(new Text("Prix Unitaire HT").setFont(bold));
        Paragraph montant=new Paragraph(new Text("Montant HT").setFont(bold));

        Paragraph valprixuni=new Paragraph(Float.toString(redevance.getMontant_redevance()));
        Paragraph valmontant=new Paragraph(Float.toString(redevance.getMontant_redevance()));

        Paragraph totalHT=new Paragraph("TOTAL HT");
        Paragraph valtotalHT=new Paragraph(Float.toString(redevance.getMontant_redevance()));
        Paragraph tva=new Paragraph("TVA 20\u0025");
        float tval=redevance.getMontant_redevance()*0.2f;
        Paragraph valtva=new Paragraph(Float.toString(tval));
        Paragraph Net=new Paragraph("Net \u00e0 payer");
        float total=redevance.getMontant_redevance()+tval;
        Paragraph valNet=new Paragraph(Float.toString(total));

        Cell QTE = new Cell()
                .setBackgroundColor(Color.LIGHT_GRAY)
                .add(qte);
        Cell DESIGN = new Cell()
                .setBackgroundColor(Color.LIGHT_GRAY)
                .add(design);
        Cell PRIXUNI = new Cell()
                .setBackgroundColor(Color.LIGHT_GRAY)
                .add(prixuni);
        Cell MONTANT = new Cell()
                .setBackgroundColor(Color.LIGHT_GRAY)
                .add(montant);
        Cell TOTALHT = new Cell()
                .setBackgroundColor(Color.LIGHT_GRAY)
                .add(totalHT);
        Cell TVA = new Cell()
                .setBackgroundColor(Color.LIGHT_GRAY)
                .add(tva);

        Cell NET = new Cell()
                .setBackgroundColor(Color.LIGHT_GRAY)
                .add(Net);


        Table table1 = new Table(
                new UnitValue[]{
                        new UnitValue(UnitValue.PERCENT, 5f),
                        new UnitValue(UnitValue.PERCENT, 35f),
                        new UnitValue(UnitValue.PERCENT, 30f),
                        new UnitValue(UnitValue.PERCENT, 30f)})
                .setWidthPercent(100);

        table1.addHeaderCell(QTE);
        table1.addHeaderCell(DESIGN);
        table1.addHeaderCell(PRIXUNI.setTextAlignment(TextAlignment.RIGHT));
        table1.addHeaderCell(MONTANT.setTextAlignment(TextAlignment.RIGHT));
        table1.addCell("1");
        table1.addCell("Emplacement: "+redevance.getId_boutique().getEmplacement().getNom());
        table1.addCell(valprixuni.setTextAlignment(TextAlignment.RIGHT));
        table1.addCell(valmontant.setTextAlignment(TextAlignment.RIGHT));
        table1.addCell(Vide);
        table1.addCell(Vide);
        table1.addCell(TOTALHT);
        table1.addCell(valtotalHT.setTextAlignment(TextAlignment.RIGHT));
        table1.addCell(Vide);
        table1.addCell(Vide);
        table1.addCell(TVA.setBackgroundColor(Color.LIGHT_GRAY));
        table1.addCell(valtva.setTextAlignment(TextAlignment.RIGHT));
        table1.addCell(Vide);
        table1.addCell(Vide);
        table1.addCell(NET);
        table1.addCell(valNet.setTextAlignment(TextAlignment.RIGHT));

        document.add(table);
        document.add(table1);

        document.close();

       return "http://localhost/pdf/facture.pdf";
    }
}
