import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;


public class PiecingItTogether {
    public static JigsawInfo getCompleteInformation(JigsawInfo input) {

        // Implement the logic to compute the missing fields based on the provided ones.
        // This is a placeholder implementation and should be replaced with actual logic.
        JigsawInfo.Builder builder = new JigsawInfo.Builder();

        // Copy existing values from input to builder
        input.getPieces().ifPresent(builder::pieces);
        input.getBorder().ifPresent(builder::border);
        input.getInside().ifPresent(builder::inside);
        input.getRows().ifPresent(builder::rows);
        input.getColumns().ifPresent(builder::columns);
        input.getAspectRatio().ifPresent(builder::aspectRatio);
        input.getFormat().ifPresent(builder::format);

        // Here you would add the logic to calculate missing fields based on the provided ones.

        OptionalInt optCols = input.getColumns();
        OptionalInt optRows = input.getRows();
        OptionalInt optPieces = input.getPieces();
        OptionalDouble optAspectRatio = input.getAspectRatio();
        OptionalInt optBorder = input.getBorder();
        OptionalInt optInside = input.getInside();
        Optional<String> optFormat = input.getFormat();


        // compute cols from square format
        if (optRows.isPresent() && optFormat.isPresent()) {
            if (optFormat.get().equals("square")) {
                if (optCols.isPresent()) {
                    if (optCols.getAsInt() != optRows.getAsInt()) {
                        throw new IllegalArgumentException("Contradictory data 1");
                    }
                }
                else {
                    optCols = OptionalInt.of(optRows.getAsInt());
                    builder.columns(optCols.getAsInt());
                }
            }
        }

        // compute roes from square format
        if (optFormat.isPresent() && optCols.isPresent()) {
            if (optFormat.get().equals("square")) {
                if (optRows.isPresent()) {
                    if (optCols.getAsInt() != optRows.getAsInt()) {
                        throw new IllegalArgumentException("Contradictory data 2");
                    }                    
                }
                else {
                    optRows = OptionalInt.of(optCols.getAsInt());
                    builder.rows(optRows.getAsInt());
                }
            }
        }

        if (optBorder.isPresent() && optPieces.isPresent() && optFormat.isPresent()) {
            double a = 2;
            double b = -1 * (4 + optBorder.getAsInt());
            double c = 2 * optPieces.getAsInt();
            double rad = b * b - 4 * a * c;
            if (rad < 0) {
                throw new IllegalArgumentException("Invalid Arguments");
            }
            int minValue = (int)Math.round((-1 * b - Math.sqrt(rad)) / (2 * a));
            int maxValue = (int)Math.round((-1 * b + Math.sqrt(rad)) / (2 * a));
            if (minValue * maxValue != optPieces.getAsInt()) {
                throw new IllegalArgumentException("Invalid Arguments");
            }
            int cols, rows;
            if (optFormat.get().equals("square")) {
                if (minValue != maxValue) {
                    throw new IllegalArgumentException("Contradictory data 15");                
                }
                cols = maxValue;
                rows = minValue;                
            }
            else if (optFormat.get().equals("landscape")) {
                cols = maxValue;
                rows = minValue;
            }
            else if (optFormat.get().equals("portrait")){
                cols = minValue;
                rows = maxValue;
            }
            else {
                throw new IllegalArgumentException("Invalid Arguments");       
            }
            if (optCols.isPresent() && optCols.getAsInt() != cols) {
                throw new IllegalArgumentException("Contradictory data 16");
            }
            builder.columns(cols);
            optCols = OptionalInt.of(cols);
            if (optRows.isPresent() && optRows.getAsInt() != rows) {
                throw new IllegalArgumentException("Contradictory data 17");
            }
            builder.rows(rows);
            optRows = OptionalInt.of(rows);
        }

        // compute rows form column and aspect ratio
        if (optRows.isPresent() && optAspectRatio.isPresent()) {
            int cols = (int)Math.round((double)optRows.getAsInt() * optAspectRatio.getAsDouble());
            if (optCols.isPresent() && optCols.getAsInt() != cols) {
                throw new IllegalArgumentException("Contradictory data 3");
            }
            builder.columns(cols);
            optCols = OptionalInt.of(cols);
        }

        // compute rows form column and aspect ratio
        if (optAspectRatio.isPresent() && optCols.isPresent()) {
            int rows = (int)Math.round((double)optCols.getAsInt() / optAspectRatio.getAsDouble());
            if (optRows.isPresent() && optRows.getAsInt() != rows) {
                throw new IllegalArgumentException("Contradictory data 4");
            }
            builder.rows(rows);
            optRows = OptionalInt.of(rows);
        }

        // compute rows form inside and aspect ratio
        if(optInside.isPresent() && optAspectRatio.isPresent()) {
            int rows = (int)Math.round(
                Math.sqrt((double)optInside.getAsInt() / optAspectRatio.getAsDouble()
            )) + 2;
            int cols = (int)Math.round(optAspectRatio.getAsDouble() * rows);
            if ((cols - 2) * (rows - 2) == optInside.getAsInt()) {
                if (optRows.isPresent() && optRows.getAsInt() == rows) {
                    throw new IllegalArgumentException("Contradictory data 5");
                }
                builder.rows(rows);
                optRows = OptionalInt.of(rows);
            }
        }

        // compute rows from inside and aspect ratio
        if(optInside.isPresent() && optAspectRatio.isPresent()) {
            int rows = (int)Math.round(
                Math.sqrt((double)optInside.getAsInt() / optAspectRatio.getAsDouble()
            )) + 2;
            int cols = (int)Math.round(optAspectRatio.getAsDouble() * rows);
            if ((cols - 2) * (rows - 2) == optInside.getAsInt()) {
                if (optCols.isPresent() && optCols.getAsInt() == cols) {
                    throw new IllegalArgumentException("Contradictory data 6");
                }
                builder.columns(cols);
                optCols = OptionalInt.of(cols);
            }
        }

        // compute pieces
        if (optCols.isPresent() && optRows.isPresent()) {
            int pieces = optCols.getAsInt() * optRows.getAsInt();
            if (optPieces.isPresent() && optPieces.getAsInt() != pieces) {
                throw new IllegalArgumentException("Contradictory data 7");
            }
            optPieces = OptionalInt.of(pieces);
            builder.pieces(optPieces.getAsInt());
        }


        // compute cols
        if (optPieces.isPresent() && optAspectRatio.isPresent()) {
            int pieces = optPieces.getAsInt();
            double aspect_ratio = optAspectRatio.getAsDouble();
            int rows = (int)Math.round(Math.sqrt((double)pieces / aspect_ratio));
            int cols = (int)Math.round(aspect_ratio * rows);
            if (rows * cols == pieces) {
                if (optCols.isPresent() && optCols.getAsInt() != cols) {
                    throw new IllegalArgumentException("Contradictory data 8");
                }
                builder.columns(cols);
                optCols = OptionalInt.of(cols);
            }
        }

        // compute rows
        if (optPieces.isPresent() && optAspectRatio.isPresent()) {
            int pieces = optPieces.getAsInt();
            double aspect_ratio = optAspectRatio.getAsDouble();
            int rows = (int)Math.round(Math.sqrt((double)pieces / aspect_ratio));
            int cols = (int)Math.round(aspect_ratio * rows);
            if (rows * cols == pieces) {
                if (optRows.isPresent() && optRows.getAsInt() != rows) {
                    throw new IllegalArgumentException("Contradictory data 9");
                }
                builder.rows(rows);
                optRows = OptionalInt.of(rows);
            }
        }

        if (optCols.isPresent() && optRows.isPresent()) {
            String format;
            if (optCols.getAsInt() > optRows.getAsInt()) {
                format = "landscape";
            }
            else if (optCols.getAsInt() < optRows.getAsInt()) {
                format = "portrait";
            }
            else {
                format = "square";
            }
            if (optFormat.isPresent() && !optFormat.get().equals(format)) {
                throw new IllegalArgumentException("Contradictory data 10");
            }
            optFormat = Optional.of(format);
            builder.format(format);
        }

        // compute aspect ratio
        if (optCols.isPresent() && optRows.isPresent()) {
            double aspectRatio = (double)optCols.getAsInt() / (double)optRows.getAsInt();
            if (optAspectRatio.isPresent() && Math.abs(optAspectRatio.getAsDouble() - aspectRatio) > 1e-4) {
                throw new IllegalArgumentException("Contradictory data 11");
            }
            builder.aspectRatio(aspectRatio);
            optAspectRatio = OptionalDouble.of(aspectRatio);
        }


        // compute border
        if (optCols.isPresent() && optRows.isPresent()) {
            int border = 2 * optCols.getAsInt() + 2 * optRows.getAsInt() - 4;
            if (optBorder.isPresent() && optBorder.getAsInt() != border) {
                throw new IllegalArgumentException("Contradictory data 12");
            }
            builder.border(border);
            optBorder = OptionalInt.of(border);
        }

        // compute inside
        if (optPieces.isPresent() && optBorder.isPresent()) {
            int inside = optPieces.getAsInt() - optBorder.getAsInt();
            if (optInside.isPresent() && optInside.getAsInt() != inside) {
                throw new IllegalArgumentException("Contradictory data 13");
            }
            builder.inside(inside);
            optInside = OptionalInt.of(inside);
        }

        // compute inside
        if (optPieces.isPresent() && optInside.isPresent() ) {
            int border = optPieces.getAsInt() - optInside.getAsInt();
            if (optBorder.isPresent() && optBorder.getAsInt() != border) {
                throw new IllegalArgumentException("Contradictory data 14");
            }
            builder.border(border);
            optBorder = OptionalInt.of(border);
        }

        if (!optCols.isPresent() || !optRows.isPresent() || !optPieces.isPresent()
            || !optAspectRatio.isPresent() || !optBorder.isPresent()
            || !optInside.isPresent() || !optFormat.isPresent()) {
            throw new IllegalArgumentException("Insufficient data");
        }

        return builder.build();

    }
}
