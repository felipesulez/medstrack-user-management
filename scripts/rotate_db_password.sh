#!/usr/bin/env bash
# Rota la clave del rol de BD, la guarda en el Llavero de macOS y deja .env.local leyendola de ahi.
set -euo pipefail
ROLE="meduser"; SERVICE="medstrack-db"
PGDATA="$(brew --prefix)/var/postgresql@14"; HBA="$PGDATA/pg_hba.conf"

[ -f "$HBA.bak" ] || cp "$HBA" "$HBA.bak"
restore() { cp "$HBA.bak" "$HBA" && rm -f "$HBA.bak"; brew services restart postgresql@14 >/dev/null; echo "Acceso a PostgreSQL cerrado de nuevo."; }
trap restore EXIT

sed -i '' -E 's/(scram-sha-256|md5|password)[[:space:]]*$/trust/' "$HBA"
brew services restart postgresql@14 >/dev/null; sleep 3

NEWPW="$(openssl rand -base64 24 | tr -d '/+=\n')"
security add-generic-password -a "$ROLE" -s "$SERVICE" -l "Medstrack DB (local)" -w "$NEWPW" -U
[ "$(security find-generic-password -a "$ROLE" -s "$SERVICE" -w)" = "$NEWPW" ] || { echo "No se pudo guardar en el Llavero. No se cambio nada en la BD."; exit 1; }
printf "ALTER ROLE %s PASSWORD :'pw';\n" "$ROLE" | psql -d postgres -v pw="$NEWPW" -v ON_ERROR_STOP=1

cat > .env.local <<'EOF'
export DB_URL='jdbc:postgresql://localhost:5432/medstrack'
export DB_USERNAME='meduser'
export DB_PASSWORD="$(security find-generic-password -a meduser -s medstrack-db -w)"
EOF
chmod 600 .env.local
echo "Clave rotada, guardada en el Llavero y .env.local actualizado."
