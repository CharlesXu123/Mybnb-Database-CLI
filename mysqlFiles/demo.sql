## listing with amenities
SELECT *
FROM listing JOIN has h on listing.lId = h.lId JOIN amenity a on a.aId = h.aId;

## listing with owner
SELECT *
FROM listing JOIN owned o on listing.lId = o.lId JOIN host h on h.uId = o.uId;

