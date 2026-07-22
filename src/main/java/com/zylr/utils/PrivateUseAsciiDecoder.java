package com.zylr.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Decodes Minescape private-use bitmap font blocks that mirror printable ASCII.
 *
 * <p>Each configured block maps offsets {@code 0x00..0x5E} to ASCII
 * {@code 0x20..0x7E}. Offset {@code 0x5F} is treated as blank.</p>
 */
public final class PrivateUseAsciiDecoder {
	private static final int PRINTABLE_ASCII_COUNT = 0x5F;
	private static final int BLANK_OFFSET = 0x5F;
	private static final int ASCII_SPACE = 0x20;
	private static final Map<Integer, Integer> EXPLICIT_CODEPOINTS = createExplicitCodepoints();

	private static final Set<Integer> BLOCK_STARTS = Set.of(
		0xE000,
		0xE0C0,
		0xE120,
		0xE180,
		0xE1E0,
		0xE240,
		0xE2A0,
		0xE300,
		0xE360,
		0xE3C0,
		0xE420,
		0xE480,
		0xE4E0,
		0xE540,
		0xE5A0
	);

	private PrivateUseAsciiDecoder() {
	}

	public static String decode(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}

		StringBuilder decoded = new StringBuilder(input.length());
		input.codePoints().forEach(codePoint -> appendDecoded(decoded, codePoint));
		return decoded.toString();
	}

	public static boolean containsEncodedAscii(String input) {
		if (input == null || input.isEmpty()) {
			return false;
		}

		return input.codePoints().anyMatch(PrivateUseAsciiDecoder::isEncodedAsciiCodePoint);
	}

	public static boolean isEncodedAsciiCodePoint(int codePoint) {
		return resolveAsciiCodePoint(codePoint) >= 0 || isBlankCodePoint(codePoint);
	}

	public static boolean isBlankCodePoint(int codePoint) {
		for (int blockStart : BLOCK_STARTS) {
			if (codePoint == blockStart + BLANK_OFFSET) {
				return true;
			}
		}
		return false;
	}

	public static int decodeCodePoint(int codePoint) {
		int asciiCodePoint = resolveAsciiCodePoint(codePoint);
		return asciiCodePoint >= 0 ? asciiCodePoint : codePoint;
	}

	public static List<String> decodeSegments(String input) {
		List<String> segments = new ArrayList<>();
		if (input == null || input.isEmpty()) {
			return segments;
		}

		StringBuilder current = new StringBuilder();
		input.codePoints().forEach(codePoint -> appendDecodedSegment(segments, current, codePoint));
		if (!current.isEmpty()) {
			segments.add(current.toString());
		}
		return segments;
	}

	private static void appendDecoded(StringBuilder decoded, int codePoint) {
		int asciiCodePoint = resolveAsciiCodePoint(codePoint);
		if (asciiCodePoint >= 0) {
			decoded.appendCodePoint(asciiCodePoint);
			return;
		}
		if (!isBlankCodePoint(codePoint)) {
			decoded.appendCodePoint(codePoint);
		}
	}

	private static void appendDecodedSegment(List<String> segments, StringBuilder current, int codePoint) {
		int asciiCodePoint = resolveAsciiCodePoint(codePoint);
		if (asciiCodePoint >= 0) {
			current.appendCodePoint(asciiCodePoint);
			return;
		}
		if (isBlankCodePoint(codePoint)) {
			if (!current.isEmpty()) {
				segments.add(current.toString());
				current.setLength(0);
			}
			return;
		}
		if (!current.isEmpty()) {
			segments.add(current.toString());
			current.setLength(0);
		}
	}

	private static int resolveAsciiCodePoint(int codePoint) {
		Integer explicitCodePoint = EXPLICIT_CODEPOINTS.get(codePoint);
		if (explicitCodePoint != null) {
			return explicitCodePoint;
		}

		for (int blockStart : BLOCK_STARTS) {
			int offset = codePoint - blockStart;
			if (offset >= 0 && offset < PRINTABLE_ASCII_COUNT) {
				return ASCII_SPACE + offset;
			}
		}
		return -1;
	}

	private static Map<Integer, Integer> createExplicitCodepoints() {
		Map<Integer, Integer> codePoints = new HashMap<>();

		// Numeric overlays observed outside the repeating private-use ASCII blocks.
		codePoints.put(0xF05A, (int) '0');
		codePoints.put(0xF05B, (int) '1');
		codePoints.put(0xF05C, (int) '2');
		codePoints.put(0xF05D, (int) '3');
		codePoints.put(0xF05E, (int) '4');
		codePoints.put(0xF05F, (int) '5');
		codePoints.put(0xF060, (int) '6');
		codePoints.put(0xF061, (int) '7');
		codePoints.put(0xF062, (int) '8');
		codePoints.put(0xF063, (int) '9');
		codePoints.put(0xF064, (int) ',');
		codePoints.put(0xF065, (int) '0');
		codePoints.put(0xF066, (int) '1');
		codePoints.put(0xF067, (int) '2');
		codePoints.put(0xF068, (int) '3');
		codePoints.put(0xF069, (int) '4');
		codePoints.put(0xF06A, (int) '5');
		codePoints.put(0xF06B, (int) '6');
		codePoints.put(0xF06C, (int) '7');
		codePoints.put(0xF06D, (int) '8');
		codePoints.put(0xF06E, (int) '9');
		codePoints.put(0xF06F, (int) ',');

		return Map.copyOf(codePoints);
	}
}
